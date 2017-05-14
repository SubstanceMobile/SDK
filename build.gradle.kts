// Import all of the Kotlin/Native gradle plugin configurations
import org.jetbrains.kotlin.gradle.plugin.*

// Include Kotlin/Native
buildscript {
    repositories {
        mavenCentral()
        maven { setUrl("https://dl.bintray.com/jetbrains/kotlin-native-dependencies") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-native-gradle-plugin:+")
    }
}

apply {
	plugin<KonanPlugin>()
}

configure<KonanInteropContainer> {

	create("gtk", closureOf<KonanInteropConfig> {
		defFile("libs/gtk.def")
		pkg("gtk")
	})

	create("time", closureOf<KonanInteropConfig> {
		defFile("libs/time.def")
		pkg("c.time")
	})

	create("stdlib", closureOf<KonanInteropConfig> {
		defFile("libs/stdlib.def")
		pkg("c.stdlib")
	})
}

configure<KonanArtifactsContainer> {
	create("gtkdemo", closureOf<KonanCompilerConfig> {
		inputDir("src/")
		outputDir("../out/")

		library

		enableOptimization() // Make smaller binaries at expense of compile time
	})
}