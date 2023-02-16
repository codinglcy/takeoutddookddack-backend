#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/server"
JAR_FILE="$PROJECT_ROOT/ddookddack-back.jar"

CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

echo "> Current port of running WAS is $CURRENT_PORT."
if [ $CURRENT_PORT -eq 8081 ]; then
  TARGET_PORT=8082
elif [ $CURRENT_PORT -eq 8082 ]; then
  TARGET_PORT=8081
else
  echo "> Not connected to nginx"
fi

echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar -Dserver.port=$TARGET_PORT $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG
