#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/ddookddackapp"
JAR_FILE="$PROJECT_ROOT/ddookddack-back.jar"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar -Dserver.port=8080 $JAR_FILE > /dev/null 2> /dev/null < /dev/null &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG

sudo service nginx reload
echo "> Nginx reloaded."
