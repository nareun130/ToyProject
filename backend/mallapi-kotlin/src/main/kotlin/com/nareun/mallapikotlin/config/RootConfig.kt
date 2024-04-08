package com.nareun.mallapikotlin.config

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RootConfig {

    @Bean
    fun modelMapper() : ModelMapper{
        val modelMapper = ModelMapper()
        modelMapper.configuration.apply {
            isFieldMatchingEnabled = true
            fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE
            matchingStrategy = MatchingStrategies.LOOSE
        }
        return modelMapper
    }
}