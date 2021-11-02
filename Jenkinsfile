def String REF=null;
def String COMMIT_ID=null;
def Boolean IS_TAG=null;
def String NEXUS_ACTUAL_REPOSITORY=null;

pipeline {
    agent {
        label "master"
    }
    tools {
        maven "Maven"
    }
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "https"
        NEXUS_URL = "nexus.seedfinding.com"
        NEXUS_REPOSITORY = "maven-seedfinding"
        NEXUS_REPOSITORY_SNAPSHOTS = "maven-seedfinding-snapshots"
        NEXUS_CREDENTIAL_ID = "nexus-user-creds"
    }
    stages {
        stage("Check Github Incoming Data"){
            steps {
                // All the variables defined here will be accessible after
                script {
                    // Get the variables from github
                    REF = env.GITHUB_REF;
                    if (REF == null || REF == "NONE" || REF == ""){
                        error("*** Github ref not defined : ${REF} ***");
                    }

                    COMMIT_ID = env.GITHUB_AFTER;
                    if (COMMIT_ID == null || COMMIT_ID == "NONE" || COMMIT_ID == ""){
                        error("*** Github commit sha1 not defined : ${COMMIT_ID} ***");
                    }

                    IS_TAG = REF.startsWith("refs/tags");

                    if (IS_TAG == null){
                        error("*** IS_TAG got an impossible value ${IS_TAG} ***");
                    }

                    NEXUS_ACTUAL_REPOSITORY = NEXUS_REPOSITORY_SNAPSHOTS;

                    if (IS_TAG == true){
                        NEXUS_ACTUAL_REPOSITORY=NEXUS_REPOSITORY;
                    }

                    if (NEXUS_ACTUAL_REPOSITORY == null){
                        error("*** NEXUS_ACTUAL_REPOSITORY got an impossible value ${NEXUS_ACTUAL_REPOSITORY} ***");
                    }

                    println("Will be uploading to ${NEXUS_ACTUAL_REPOSITORY} (tag:${IS_TAG})")
                }
            }
        }

        stage("Clone code from VCS") {
            steps {
                script {
                    git branch: 'main',
                        url:'https://github.com/SeedFinding/mc_feature_java.git';

                }
            }
        }

        stage("Maven Build commit") {
            when {
                expression {
                    return IS_TAG == false;
                }
            }
            steps {
                withCredentials([file(credentialsId: '300', variable: 'FILE'),
                                 string(credentialsId: '301', variable: 'PASSWORD'),
                                 string(credentialsId: '302', variable: 'KEY_ID')]) {
                    script {
                        sh("chmod +x gradlew");
                        sh("./gradlew clean");
                        sh("./gradlew publishMavenJavaPublicationToMavenLocal -Pversion=${COMMIT_ID}" + ' -Psigning.secretKeyRingFile=$FILE -Psigning.password=$PASSWORD -Psigning.keyId=$KEY_ID');
                    }
                }
            }
        }
        stage("Publish to Nexus Repository Manager for commit") {
            when {
                    expression {
                        return IS_TAG == false;
                    }
            }
            steps {
                script {
                    // Read the pom file

                    def pomPath="build/publications/mavenJava/pom-default.xml";
                    pom = readMavenPom(file: pomPath);
                    if (pom==null) {
                        error("*** Pom file could not be read at ${pomPath} ***");
                    }

                    // Get the jars

                    jarsByGlob = findFiles(glob: "build/libs/*.jar");
                    println(jarsByGlob)
                    if (jarsByGlob.length != 3){
                         error("*** Missing a jar ${jarsByGlob.length} (should be 3) ***");
                    }

                    echo("0: ${jarsByGlob[0].name} ${jarsByGlob[0].path} ${jarsByGlob[0].directory} ${jarsByGlob[0].length} ${jarsByGlob[0].lastModified}");
                    echo("1: ${jarsByGlob[1].name} ${jarsByGlob[1].path} ${jarsByGlob[1].directory} ${jarsByGlob[1].length} ${jarsByGlob[1].lastModified}");
                    echo("2: ${jarsByGlob[2].name} ${jarsByGlob[2].path} ${jarsByGlob[2].directory} ${jarsByGlob[2].length} ${jarsByGlob[2].lastModified}");

                    javadocPath  = jarsByGlob[0].path;
                    sourcePath   = jarsByGlob[1].path;
                    artifactPath = jarsByGlob[2].path;

                    if(!(fileExists(artifactPath) && fileExists(javadocPath) && fileExists(sourcePath))) {
                         error ("*** One or more files out of ${javadocPath}, ${sourcePath} and ${artifactPath} could not be found ***");
                    }

                    // Get the signatures

                    signaturesByGlob = findFiles(glob: "build/libs/*.asc");

                    echo("0: ${signaturesByGlob[0].name} ${signaturesByGlob[0].path} ${signaturesByGlob[0].directory} ${signaturesByGlob[0].length} ${signaturesByGlob[0].lastModified}");
                    echo("1: ${signaturesByGlob[1].name} ${signaturesByGlob[1].path} ${signaturesByGlob[1].directory} ${signaturesByGlob[1].length} ${signaturesByGlob[1].lastModified}");
                    echo("2: ${signaturesByGlob[2].name} ${signaturesByGlob[2].path} ${signaturesByGlob[2].directory} ${signaturesByGlob[2].length} ${signaturesByGlob[2].lastModified}");

                    javadocSignaturePath  = signaturesByGlob[0].path;
                    sourceSignaturePath   = signaturesByGlob[1].path;
                    artifactSignaturePath = signaturesByGlob[2].path;

                    if(!(fileExists(artifactSignaturePath) && fileExists(javadocSignaturePath) && fileExists(sourceSignaturePath))) {
                         error ("*** One or more files out of ${javadocSignaturePath}, ${sourceSignaturePath} and ${artifactSignaturePath} could not be found ***");
                    }

                    // Upload to Nexus

                    echo "*** File JAR: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                    echo "*** File JAVADOC: ${javadocPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                    echo "*** File SOURCES: ${sourcePath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                    nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_ACTUAL_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: 'javadoc',
                                file: javadocPath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: 'sources',
                                file: sourcePath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: pomPath,
                                type: "pom"],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactSignaturePath,
                                type: "asc"],
                                [artifactId: pom.artifactId,
                                classifier: 'javadoc',
                                file: javadocSignaturePath,
                                type: "asc"],
                                [artifactId: pom.artifactId,
                                classifier: 'sources',
                                file: sourceSignaturePath,
                                type: "asc"],
                            ]
                    );

                }
            }
        }

        stage("Maven Build version") {
            when {
                expression {
                    return IS_TAG == true;
                }
            }
            steps {
                withCredentials([file(credentialsId: '300', variable: 'FILE'),
                                 string(credentialsId: '301', variable: 'PASSWORD'),
                                 string(credentialsId: '302', variable: 'KEY_ID')]) {
                    script {
                        sh("chmod +x gradlew");
                        sh("./gradlew clean");
                        sh('./gradlew publishMavenJavaPublicationToMavenLocal -Psigning.secretKeyRingFile=$FILE -Psigning.password=$PASSWORD -Psigning.keyId=$KEY_ID');
                    }
                }
            }
        }
        stage("Publish to Nexus Repository Manager for version") {
            when {
                    expression {
                        return IS_TAG == true;
                    }
            }
            steps {
                script {
                    // Read the pom file

                    def pomPath="build/publications/mavenJava/pom-default.xml";
                    pom = readMavenPom(file: pomPath);
                    if (pom==null) {
                        error("*** Pom file could not be read at ${pomPath} ***");
                    }

                    // Get the jars

                    jarsByGlob = findFiles(glob: "build/libs/*.jar");
                    println(jarsByGlob)
                    if (jarsByGlob.length != 3){
                         error("*** Missing a jar ${jarsByGlob.length} (should be 3) ***");
                    }

                    echo("0: ${jarsByGlob[0].name} ${jarsByGlob[0].path} ${jarsByGlob[0].directory} ${jarsByGlob[0].length} ${jarsByGlob[0].lastModified}");
                    echo("1: ${jarsByGlob[1].name} ${jarsByGlob[1].path} ${jarsByGlob[1].directory} ${jarsByGlob[1].length} ${jarsByGlob[1].lastModified}");
                    echo("2: ${jarsByGlob[2].name} ${jarsByGlob[2].path} ${jarsByGlob[2].directory} ${jarsByGlob[2].length} ${jarsByGlob[2].lastModified}");

                    javadocPath  = jarsByGlob[0].path;
                    sourcePath   = jarsByGlob[1].path;
                    artifactPath = jarsByGlob[2].path;

                    if(!(fileExists(artifactPath) && fileExists(javadocPath) && fileExists(sourcePath))) {
                         error ("*** One or more files out of ${javadocPath}, ${sourcePath} and ${artifactPath} could not be found ***");
                    }

                    // Get the signatures

                    signaturesByGlob = findFiles(glob: "build/libs/*.asc");

                    echo("0: ${signaturesByGlob[0].name} ${signaturesByGlob[0].path} ${signaturesByGlob[0].directory} ${signaturesByGlob[0].length} ${signaturesByGlob[0].lastModified}");
                    echo("1: ${signaturesByGlob[1].name} ${signaturesByGlob[1].path} ${signaturesByGlob[1].directory} ${signaturesByGlob[1].length} ${signaturesByGlob[1].lastModified}");
                    echo("2: ${signaturesByGlob[2].name} ${signaturesByGlob[2].path} ${signaturesByGlob[2].directory} ${signaturesByGlob[2].length} ${signaturesByGlob[2].lastModified}");

                    javadocSignaturePath  = signaturesByGlob[0].path;
                    sourceSignaturePath   = signaturesByGlob[1].path;
                    artifactSignaturePath = signaturesByGlob[2].path;

                    if(!(fileExists(artifactSignaturePath) && fileExists(javadocSignaturePath) && fileExists(sourceSignaturePath))) {
                         error ("*** One or more files out of ${javadocSignaturePath}, ${sourceSignaturePath} and ${artifactSignaturePath} could not be found ***");
                    }

                    // Upload to Nexus

                    echo "*** File JAR: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                    echo "*** File JAVADOC: ${javadocPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                    echo "*** File SOURCES: ${sourcePath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                    nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_ACTUAL_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: 'javadoc',
                                file: javadocPath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: 'sources',
                                file: sourcePath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: pomPath,
                                type: "pom"],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactSignaturePath,
                                type: "asc"],
                                [artifactId: pom.artifactId,
                                classifier: 'javadoc',
                                file: javadocSignaturePath,
                                type: "asc"],
                                [artifactId: pom.artifactId,
                                classifier: 'sources',
                                file: sourceSignaturePath,
                                type: "asc"],
                            ]
                    );

                }
            }
        }

        stage("Maven Build latest") {
            steps {
                withCredentials([file(credentialsId: '300', variable: 'FILE'),
                                 string(credentialsId: '301', variable: 'PASSWORD'),
                                 string(credentialsId: '302', variable: 'KEY_ID')]) {
                    script {
                        sh("chmod +x gradlew");
                        sh("./gradlew clean");
                        sh('./gradlew publishMavenJavaPublicationToMavenLocal -Pversion=LATEST -Psigning.secretKeyRingFile=$FILE -Psigning.password=$PASSWORD -Psigning.keyId=$KEY_ID');
                    }
                }
            }
        }
        stage("Publish to Nexus Repository Manager for latest") {
            steps {
                script {
                    // Read the pom file

                    def pomPath="build/publications/mavenJava/pom-default.xml";
                    pom = readMavenPom(file: pomPath);
                    if (pom==null) {
                        error("*** Pom file could not be read at ${pomPath} ***");
                    }

                    // Get the jars

                    jarsByGlob = findFiles(glob: "build/libs/*.jar");
                    println(jarsByGlob)
                    if (jarsByGlob.length != 3){
                         error("*** Missing a jar ${jarsByGlob.length} (should be 3) ***");
                    }

                    echo("0: ${jarsByGlob[0].name} ${jarsByGlob[0].path} ${jarsByGlob[0].directory} ${jarsByGlob[0].length} ${jarsByGlob[0].lastModified}");
                    echo("1: ${jarsByGlob[1].name} ${jarsByGlob[1].path} ${jarsByGlob[1].directory} ${jarsByGlob[1].length} ${jarsByGlob[1].lastModified}");
                    echo("2: ${jarsByGlob[2].name} ${jarsByGlob[2].path} ${jarsByGlob[2].directory} ${jarsByGlob[2].length} ${jarsByGlob[2].lastModified}");

                    javadocPath  = jarsByGlob[0].path;
                    sourcePath   = jarsByGlob[1].path;
                    artifactPath = jarsByGlob[2].path;

                    if(!(fileExists(artifactPath) && fileExists(javadocPath) && fileExists(sourcePath))) {
                         error ("*** One or more files out of ${javadocPath}, ${sourcePath} and ${artifactPath} could not be found ***");
                    }

                    // Get the signatures

                    signaturesByGlob = findFiles(glob: "build/libs/*.asc");

                    echo("0: ${signaturesByGlob[0].name} ${signaturesByGlob[0].path} ${signaturesByGlob[0].directory} ${signaturesByGlob[0].length} ${signaturesByGlob[0].lastModified}");
                    echo("1: ${signaturesByGlob[1].name} ${signaturesByGlob[1].path} ${signaturesByGlob[1].directory} ${signaturesByGlob[1].length} ${signaturesByGlob[1].lastModified}");
                    echo("2: ${signaturesByGlob[2].name} ${signaturesByGlob[2].path} ${signaturesByGlob[2].directory} ${signaturesByGlob[2].length} ${signaturesByGlob[2].lastModified}");

                    javadocSignaturePath  = signaturesByGlob[0].path;
                    sourceSignaturePath   = signaturesByGlob[1].path;
                    artifactSignaturePath = signaturesByGlob[2].path;

                    if(!(fileExists(artifactSignaturePath) && fileExists(javadocSignaturePath) && fileExists(sourceSignaturePath))) {
                         error ("*** One or more files out of ${javadocSignaturePath}, ${sourceSignaturePath} and ${artifactSignaturePath} could not be found ***");
                    }

                    // Upload to Nexus

                    echo "*** File JAR: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                    echo "*** File JAVADOC: ${javadocPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                    echo "*** File SOURCES: ${sourcePath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                    nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_ACTUAL_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: 'javadoc',
                                file: javadocPath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: 'sources',
                                file: sourcePath,
                                type: "jar"],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: pomPath,
                                type: "pom"],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactSignaturePath,
                                type: "asc"],
                                [artifactId: pom.artifactId,
                                classifier: 'javadoc',
                                file: javadocSignaturePath,
                                type: "asc"],
                                [artifactId: pom.artifactId,
                                classifier: 'sources',
                                file: sourceSignaturePath,
                                type: "asc"],
                            ]
                    );

                }
            }
        }
    }
}
