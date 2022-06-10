package org.example.project.adapter.spring.reactive

import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@AutoConfigureBefore
@AutoConfigureAfter
@ComponentScan
class SpringRestReactiveAutoConfiguration {
}
