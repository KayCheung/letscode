import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuc on 2016/1/14.
 */
public class abc {
    public static final int FAILURE_TIME_SPAN = 10;

    public static long idealStartTs(long endTs) {
        long startTs = endTs - FAILURE_TIME_SPAN + 1;//10-->1
        return startTs;
    }

    public static boolean logLongEnough(long firstOccur, long tailOccur) {
        //1--10->bad;1--11->good
        return tailOccur - firstOccur >= FAILURE_TIME_SPAN;
    }

    public static long idealEndTs(long startTs) {
        long endTs = startTs + FAILURE_TIME_SPAN - 1;//11-->20
        return startTs;
    }

    public static class CountWithinSecond {
        public long ts;
        public int total;
        public int _500;
    }

    public static class TimeSpanCount {
        public long idealTs = -1L;
        public int total = 0;
        public int _500 = 0;
    }

    public enum HowLong {UNKNOWN, LT_EQ, GT}

    public enum MoveDirection {EARLIER_TO_LEFT, LATTER_TO_RIGHT}

    public static class CalcErrorTime {
        public HowLong hl = HowLong.UNKNOWN;
        public List<CountWithinSecond> left = new ArrayList<>(FAILURE_TIME_SPAN + 1);
        public List<CountWithinSecond> right = new ArrayList<>(FAILURE_TIME_SPAN + 1);

        public TimeSpanCount lastErrorStart = new TimeSpanCount();

        public TimeSpanCount lastTSC = new TimeSpanCount();

        public long errorTimeByFar = 0L;

        /**
         * 给定一个时间戳，获取其所在的 index
         * <p>
         * 1. 能恰好找到，最好
         * <p>
         * 2. 根本找不到
         * LATTER_TO_RIGHT：找到 比它 大的那个index，然后 -1
         * EARLIER_TO_LEFT：找到 比它 小的那个index，然后 +1
         * 如果和 standOnIndex 一样，则，直接返回-1
         *
         * @param listcws
         * @param standOnIndex
         * @param md
         * @param targetTs
         * @return
         */
        public static int findIndexByTs(List<CountWithinSecond> listcws, int standOnIndex, MoveDirection md, long targetTs) {
            if (md == MoveDirection.LATTER_TO_RIGHT) {
                for (int i = standOnIndex + 1; i < listcws.size(); i++) {
                    long ts = listcws.get(i).ts;
                    if (ts == targetTs) {
                        return i;
                    } else if (ts > targetTs) {
                        return i - 1;
                    }
                    // ts<targetTs。继续循环
                    else {

                    }
                }
            }
            if (md == MoveDirection.EARLIER_TO_LEFT) {
                for (int i = standOnIndex - 1; i >= 0; i--) {
                    long ts = listcws.get(i).ts;

                    if (ts == targetTs) {
                        return i;
                    } else if (ts < targetTs) {
                        return i + 1;
                    }
                    // ts>targetTs。继续循环
                    else {

                    }
                }

            }
            throw new RuntimeException("Should never happen, should be able to find targetTs");

        }

        public static int[] crossOverIndices(List<CountWithinSecond> listcws, int standOnIndex, MoveDirection md, long seconds) {
            List<Integer> listIndices = new ArrayList<>(FAILURE_TIME_SPAN);
            long standOnMoment = listcws.get(standOnIndex).ts;

            if (md == MoveDirection.LATTER_TO_RIGHT) {
                long bigReachMoment = standOnMoment + seconds;
                for (int i = standOnIndex + 1; i < listcws.size(); i++) {
                    if (listcws.get(i).ts <= bigReachMoment) {
                        listIndices.add(i);
                    } else {
                        break;
                    }
                }
                return list2array(listIndices);
            }

            if (md == MoveDirection.EARLIER_TO_LEFT) {
                long smallReachMoment = standOnMoment - seconds;
                for (int i = standOnIndex - 1; i >= 0; i--) {
                    if (listcws.get(i).ts >= smallReachMoment) {
                        listIndices.add(i);
                    } else {
                        break;
                    }
                }
                return list2array(listIndices);
            }
            return new int[0];
        }

        private static int[] list2array(List<Integer> listIndices) {
            int[] array = new int[listIndices.size()];
            for (int i = 0; i < listIndices.size(); i++) {
                array[i] = listIndices.get(i).intValue();
            }
            return array;
        }


        public CountWithinSecond getRightFirst() {
            if (right.isEmpty()) {
                return null;
            }
            return right.get(0);
        }

        public CountWithinSecond getRightLast() {
            if (right.isEmpty()) {
                return null;
            }
            return right.get(right.size() - 1);
        }

        public int getRightSize() {
            return right.size();
        }

        public boolean leftEmpty() {
            return left.isEmpty();
        }

        private boolean goon() {
            if (hl == HowLong.UNKNOWN) {
                CountWithinSecond appear = getRightFirst();
                CountWithinSecond vanish = getRightLast();
                hl = (appear == null) ? HowLong.LT_EQ :
                        (logLongEnough(appear.ts, vanish.ts) ? HowLong.GT : HowLong.LT_EQ);
            }
            if (hl == HowLong.GT) {
                return true;
            }
            if (hl == HowLong.LT_EQ) {
                return false;
            }
            return false;
        }

        public int[] accumulateCount(List<CountWithinSecond> listcws, int startIndex, int endIndex) {
            int[] totalAnd500 = new int[]{0, 0};
            for (int i = startIndex; i <= endIndex; i++) {
                CountWithinSecond cws = listcws.get(i);
                totalAnd500[0] += cws.total;
                totalAnd500[1] += cws._500;
            }
            return totalAnd500;
        }

        public void yesDoCalc() {
            if (!goon()) {
                return;
            }

            if (leftEmpty()) {
                CountWithinSecond appear = getRightFirst();
                long startTs = appear.ts;
                long idealTs = idealEndTs(startTs);
                int endIndex = findIndexByTs(right, 0, MoveDirection.LATTER_TO_RIGHT, idealTs);

                int[] totalAnd500 = accumulateCount(right, 0, endIndex);
                //终于计算出来了 第一个
                TimeSpanCount tsc = new TimeSpanCount();
                tsc.idealTs = idealTs;
                tsc.total = totalAnd500[0];
                tsc._500 = totalAnd500[1];


                // 很好，后面需要在计算 idealTs后面所有的那些的 TimeSpanCount

            } else {

            }

        }


    }


}
