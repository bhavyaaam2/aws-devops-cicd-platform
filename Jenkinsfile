pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvnw.cmd test'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Spring Boot application...'
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

    }

    post {

        success {
            echo 'CI Pipeline completed successfully!'
        }

        failure {
            echo 'CI Pipeline failed!'
        }

    }
}