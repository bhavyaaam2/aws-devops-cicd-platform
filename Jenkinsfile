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

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                bat 'docker build -t aws-devops-cicd-platform:latest .'
            }
        }
        stage('AWS Check') {
            steps {
                echo 'Checking AWS CLI access...'
                bat '"C:\\Program Files\\Amazon\\AWSCLIV2\\aws.exe" --version'
                bat '"C:\\Program Files\\Amazon\\AWSCLIV2\\aws.exe" sts get-caller-identity'
            }
        }
        stage('ECR Push') {
            steps {
                echo 'Logging in to AWS ECR...'
                bat '"C:\\Program Files\\Amazon\\AWSCLIV2\\aws.exe" ecr get-login-password --region ap-southeast-2 | docker login --username AWS --password-stdin 351473831892.dkr.ecr.ap-southeast-2.amazonaws.com'

                echo 'Tagging Docker image for ECR...'
                bat 'docker tag aws-devops-cicd-platform:latest 351473831892.dkr.ecr.ap-southeast-2.amazonaws.com/aws-devops-cicd-platform:latest'

                echo 'Pushing Docker image to ECR...'
                bat 'docker push 351473831892.dkr.ecr.ap-southeast-2.amazonaws.com/aws-devops-cicd-platform:latest'
            }
        }
        stage('ECS Deploy') {
            steps {
                echo 'Deploying application to ECS...'

                bat '"C:\\Program Files\\Amazon\\AWSCLIV2\\aws.exe" ecs update-service --cluster aws-devops-cicd-cluster --service aws-devops-cicd-service --force-new-deployment --region ap-southeast-2'
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