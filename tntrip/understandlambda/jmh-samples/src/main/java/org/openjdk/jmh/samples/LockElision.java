/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.openjdk.jmh.samples;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@OperationsPerInvocation(10000)
public class LockElision {

    @State(Scope.Thread)
    public static class SyncWrapper {
        private final SynchronizedSB thesb = new SynchronizedSB();
    }

    @State(Scope.Thread)
    public static class UnsyncWrapper {
        private final OrgnSB thesb = new OrgnSB();
    }
    @Benchmark
    @Fork(jvmArgsAppend = {
            "-server", "-XX:+DoEscapeAnalysis",
            "-XX:+EliminateLocks","-XX:+PrintFlagsFinal"}, value = 1)
    public StringBuilder sycn_Escape_Eliminate(SyncWrapper sw) {
        int count = 10000;
        //SynchronizedSB thesb = sw.thesb;
//                new SynchronizedSB();
        for (int i = 0; i < count; i++) {
            sw.thesb.append("abc");
            sw.thesb.delete(0, sw.thesb.length());
        }
        return sw.thesb.sb;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(jvmArgsAppend = {
            "-server", "-XX:+DoEscapeAnalysis",
            "-XX:-EliminateLocks"}, value = 1)
    public StringBuilder sycn_Escape_NoEliminate(SyncWrapper sw) {
        int count = 10000;
        SynchronizedSB thesb = sw.thesb;
//                new SynchronizedSB();
        for (int i = 0; i < count; i++) {
            thesb.append("abc");
            thesb.delete(0, thesb.length());
        }
        return thesb.sb;
    }

    @Benchmark
    @Fork(jvmArgsAppend = {
            "-server", "-XX:-DoEscapeAnalysis",
            "-XX:+EliminateLocks"}, value = 1)
    public StringBuilder sycn_NoEscape_Eliminate(SyncWrapper sw) {
        int count = 10000;
        SynchronizedSB thesb = sw.thesb;
//                new SynchronizedSB();
        for (int i = 0; i < count; i++) {
            thesb.append("abc");
            thesb.delete(0, thesb.length());
        }
        return thesb.sb;
    }

    @Benchmark
    @Fork(jvmArgsAppend = {
            "-server", "-XX:-DoEscapeAnalysis",
            "-XX:-EliminateLocks"}, value = 1)
    public StringBuilder sycn_NoEscape_NoEliminate(SyncWrapper sw) {
        int count = 10000;
        SynchronizedSB thesb = sw.thesb;
//                new SynchronizedSB();
        for (int i = 0; i < count; i++) {
            thesb.append("abc");
            thesb.delete(0, thesb.length());
        }
        return thesb.sb;
    }
//
//    @Benchmark
//    @Fork(jvmArgsAppend = {
//            "-server", "-XX:+DoEscapeAnalysis",
//            "-XX:+EliminateLocks"}, value = 1)
//    public StringBuilder unsycn_Escape_Eliminate() {
//        int count = 10000;
//        OrgnSB thesb = new OrgnSB();
//        for (int i = 0; i < count; i++) {
//            thesb.append("abc");
//            thesb.delete(0, thesb.length());
//        }
//        return thesb.sb;
//    }
//
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    @Fork(jvmArgsAppend = {
//            "-server", "-XX:+DoEscapeAnalysis",
//            "-XX:-EliminateLocks"}, value = 1)
//    public StringBuilder unsycn_Escape_NoEliminate() {
//        int count = 10000;
//        OrgnSB thesb = new OrgnSB();
//        for (int i = 0; i < count; i++) {
//            thesb.append("abc");
//            thesb.delete(0, thesb.length());
//        }
//        return thesb.sb;
//    }
//
//    @Benchmark
//    @Fork(jvmArgsAppend = {
//            "-server", "-XX:-DoEscapeAnalysis",
//            "-XX:+EliminateLocks"}, value = 1)
//    @Warmup(iterations = 5, time = 1500, timeUnit = TimeUnit.MILLISECONDS)
//    @Measurement(iterations = 5, time = 1500, timeUnit = TimeUnit.MILLISECONDS)
//    public StringBuilder unsycn_NoEscape_Eliminate() {
//        int count = 10000;
//        OrgnSB thesb = new OrgnSB();
//        for (int i = 0; i < count; i++) {
//            thesb.append("abc");
//            thesb.delete(0, thesb.length());
//        }
//        return thesb.sb;
//    }
//
//    @Benchmark
//    @Fork(jvmArgsAppend = {
//            "-server", "-XX:-DoEscapeAnalysis",
//            "-XX:-EliminateLocks"}, value = 1)
//    public StringBuilder unsycn_NoEscape_NoEliminate() {
//        int count = 10000;
//        OrgnSB thesb = new OrgnSB();
//        for (int i = 0; i < count; i++) {
//            thesb.append("abc");
//            thesb.delete(0, thesb.length());
//        }
//        return thesb.sb;
//    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * Note JMH honors the default annotation settings. You can always override
     * the defaults via the command line or API.
     *
     * You can run this test:
     *
     * a) Via the command line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_20
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LockElision.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}
