plugins {
  val kotlinVer = "1.4-M3"
  kotlin("multiplatform") version kotlinVer
  kotlin("plugin.serialization") version kotlinVer
}
group = "me.cottand"
version = "1.0-SNAPSHOT"

val serializationVersion = "0.20.0"

repositories {
  mavenCentral()
  maven {
    url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
  }
}
kotlin {
  jvm {
    compilations.all {
      kotlinOptions.jvmTarget = "1.8"
    }
  }
  js {
    browser {
      testTask {
        useKarma {
          useChromeHeadless()
          webpackConfig.cssSupport.enabled = true
        }
      }
    }
  }
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation(kotlin("stdlib-common"))
        implementation(
          "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion"
        )
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
    val jvmMain by getting {
      dependencies {
        implementation(kotlin("stdlib-jdk8"))
            implementation(
              "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion"
            ) // JVM dependency

      }
    }
    val jvmTest by getting {
      dependencies {
        implementation(kotlin("test-junit5"))
      }
    }
    val jsMain by getting {
      dependencies {
        implementation(kotlin("stdlib-js"))
        implementation(
          "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serializationVersion"
        )
      }
    }
    val jsTest by getting {
      dependencies {
        implementation(kotlin("test-js"))
      }
    }
  }
}
