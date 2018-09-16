package com.example.tracerboxthree;

import com.uber.jaeger.Tracer.Builder;
import com.uber.jaeger.metrics.Metrics;
import com.uber.jaeger.metrics.NullStatsReporter;
import com.uber.jaeger.metrics.StatsFactoryImpl;
import com.uber.jaeger.propagation.b3.B3TextMapCodec;
import com.uber.jaeger.reporters.RemoteReporter;
import com.uber.jaeger.samplers.ConstSampler;
import com.uber.jaeger.senders.HttpSender;
import io.opentracing.propagation.Format;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TracerBoxThreeApplication {

    @Bean
    public io.opentracing.Tracer jaegerTracer() {
        Builder builder = new Builder("tracer-box-three",
                new RemoteReporter(new HttpSender("http://jaeger-collector.istio-system:14268/api/traces"), 10,
                        65000, new Metrics(new StatsFactoryImpl(new NullStatsReporter()))),
                new ConstSampler(true))
                .registerInjector(Format.Builtin.HTTP_HEADERS, new B3TextMapCodec())
                .registerExtractor(Format.Builtin.HTTP_HEADERS, new B3TextMapCodec());
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(TracerBoxThreeApplication.class, args);
    }
}
