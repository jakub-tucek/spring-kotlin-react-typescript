import com.moowork.gradle.node.yarn.YarnTask
import com.moowork.gradle.node.task.NodeTask
import com.moowork.gradle.node.yarn.YarnInstallTask
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun


tasks {
    val resourcesPath = "$rootDir/web/src/main/resources"
    val reactTemplatePath = "$resourcesPath/templates/react.mustache"
    val reactExampleTemplatePath = "$reactTemplatePath.example"

    withType<BootJar> {
        enabled = true
    }
    withType<BootRun> {
        enabled = true
    }
    withType<YarnTask> {
        setWorkingDir(File("$rootDir/web-react"))
    }

    // Task that checks if react.mustache exists
    // And if not creates new one from example file
    create("initReactTemplate") {
        group = "js"
        doLast {
            if (!File(reactTemplatePath).exists()) {
                println("react.mustache does not exist, creating new one")
                File(reactExampleTemplatePath).copyTo(File(reactTemplatePath))
            }
        }
    }
    val cleanJsFiles = create("cleanJsFiles") {
        group = "js"
        description = "Cleans javascript bundled files"
        doLast {
            val staticFolder = File("$resourcesPath/static")
            staticFolder.listFiles()
                    .filter { f -> f.name.matches("""src\..*\.(js|map)""".toRegex()) }
                    .forEach { f ->
                        println("deleting file: ${f.name}")
                        f.delete()
                    }
            File(reactTemplatePath).delete()
        }
    }
    val installJs = create<YarnTask>("installJs") {
        group = "js"
        description = "Install javascript depedencies"
    }
    val buildJs = create<YarnTask>("buildJs") {
        group = "js"
        args = mutableListOf("build:spring")
        description = "Bundle production version and saves it to resources"
        dependsOn(installJs)
    }
    create<YarnTask>("testJs") {
        group = "js"
        args = mutableListOf("test")
        description = "Runs javascript tests"
        dependsOn(installJs)
    }

    getByName("processResources") {
        dependsOn(buildJs)
    }
    getByName("clean").dependsOn(cleanJsFiles)

}


dependencies {
    val compile by configurations

    compile(project(":persistence"))
}
