pipeline {
    agent any

    options {
        githubProjectProperty(projectUrlStr: "https://github.com/SirBlobman/Gemmary")
    }

    triggers {
        githubPush()
    }

    tools {
        jdk "JDK 17"
    }

    stages {
        stage("Gradle: Build") {
            steps {
                withGradle {
                    sh("./gradlew clean build --no-daemon")
                }
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'build/libs/gemmary-*.jar', fingerprint: true
        }

        always {
            script {
                discordSend webhookURL: DISCORD_URL, title: "Gemmary Mod", link: "${env.BUILD_URL}",
                        result: currentBuild.currentResult,
                        description: """\
                                **Branch:** ${env.GIT_BRANCH}
                                **Build:** ${env.BUILD_NUMBER}
                                **Status:** ${currentBuild.currentResult}""".stripIndent(),
                        enableArtifactsList: false, showChangeset: true
            }
        }
    }
}
