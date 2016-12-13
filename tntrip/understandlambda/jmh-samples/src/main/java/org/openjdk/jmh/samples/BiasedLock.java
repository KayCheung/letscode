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
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
public class BiasedLock {
    List<Integer> numberList = new MyList<>();

    double x1 = Math.PI;

    /*
     * In addition to all the command line options usable at run time,
     * we have the annotations which can provide the reasonable defaults
     * for the some of the benchmarks. This is very useful when you are
     * dealing with lots of benchmarks, and some of them require
     * special treatment.
     *
     * Annotation can also be placed on class, to have the effect over
     * all the benchmark methods in the same class. The rule is, the
     * annotation in the closest scope takes the precedence: i.e.
     * the method-based annotation overrides class-based annotation,
     * etc.
     */

    //@Benchmark
    @Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 20, time = 300, timeUnit = TimeUnit.MILLISECONDS)
    public void addValues() {
        numberList.add(123);
        numberList.clear();
    }

//        SynchronizedSB thesb = new SynchronizedSB();
    OrgnSB thesb = new OrgnSB();
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(jvmArgsAppend = {"-XX:+UseBiasedLocking",
            "-XX:BiasedLockingStartupDelay=0",
            "-server",
            "-XX:+EliminateLocks"})
    public void stringBuilderTest() {
//        SynchronizedSB thesb = this.thesb;
        OrgnSB thesb = this.thesb;
//        SynchronizedSB thesb = new SynchronizedSB();
//    OrgnSB thesb = new OrgnSB();
        for (int i = 0; i < 10000; i++) {
            thesb.append("abc");
            thesb.delete(0, thesb.length());
        }
    }

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
                .include(BiasedLock.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}
