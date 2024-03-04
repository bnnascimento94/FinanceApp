pipeline {

    agent {
        docker {
            image 'androidsdk/android-30'
        }
    }

    /* agent { label 'mac' } */

    environment {
        branch = 'multi_module'
        url = 'https://github.com/bnnascimento94/FinanceApp.git'
    }

    stages {

        stage('Initialize'){
            def dockerHome = tool 'myDocker'
            env.PATH = "${dockerHome}/bin:${env.PATH}"
        }

        stage('Checkout git') {
            steps {
                git branch: branch, credentialsId: 'gitHubCredential', url: url
            }
        }

        stage('Lint') {
            steps {
                sh "./gradlew lint"
            }
        }

        stage('Test') {
            steps {
                sh "./gradlew test --stacktrace"
            }
        }

        // Manage Jenkins > Credentials > Add > Secret file or Secret Text
        stage('Credentials') {
            steps {
                withCredentials([file(credentialsId: 'ANDROID_KEYSTORE_FILE', variable: 'ANDROID_KEYSTORE_FILE')]) {
                    sh "cp '${ANDROID_KEYSTORE_FILE}' app/financeApp.jks"
                    sh "cat app/financeApp.jks"
                }
                withCredentials([file(credentialsId: 'GOOGLE_SERVICE_ACCOUNT_FILE', variable: 'GOOGLE_SERVICE_ACCOUNT_FILE')]) {
                    sh "cp '${GOOGLE_SERVICE_ACCOUNT_FILE}' app/service-account.json"
                    sh "cat app/service-account.json"
                }
            }
        }

        stage('Build apk') {
            steps {
                sh "./gradlew assembleRelease"
            }
        }

        stage('Build bundle') {
            steps {
                sh "./gradlew bundleRelease"
            }
        }

        stage('Publish') {
            parallel {
                stage('Google Play...') {
                    steps {
                        sh "./gradlew publishBundle"
                    }
                }
            }
        }
    }
}
