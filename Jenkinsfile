pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Alexpm27/microservices-test.git'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Deploy') {
            steps {
                // Aquí puedes añadir los pasos para desplegar tu aplicación
                // Por ejemplo, puedes usar Docker, Kubernetes, SCP, etc.
                echo 'Desplegando aplicación...'
                script {
                    // Detener cualquier instancia existente de la aplicación
                    sh 'pkill -f "java -jar" || true'

                    // Ejecutar la nueva versión de la aplicación
                    sh 'nohup java -jar target/test-service.jar > app.log 2>&1 &'}
            }
        }
    }

    post {
        success {
            echo 'Pipeline completado con éxito.'
        }
        failure {
            echo 'Pipeline falló.'
        }
    }
}
