package com.example

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.config.Config
import grails.util.Holders
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

@CompileStatic
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @CompileDynamic
    @Override
    void onStartup(Map<String, Object> event) {
        Config config = Holders.grailsApplication.config

        // Workaround for https://github.com/grails/grails-core/issues/12953
        if (config.getProperty('hack.suppressGrailsCodegenDefaultPackage', Boolean.class)) {
            config.remove('grails.codegen.defaultPackage')
            config.get('grails')?.remove('codegen')
        }
    }
}
