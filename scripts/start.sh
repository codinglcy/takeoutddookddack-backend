#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/ddookddackapp"
JAR_FILE="$PROJECT_ROOT/ddookddack-back.jar"

CURRENT_PORT=$(cat /home/ubuntu/service_url.inc | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

if [ ${CURRENT_PORT} -eq 8000 ]; then
  TARGET_PORT=8001
elif [ ${CURRENT_PORT} -eq 8001 ]; then
  TARGET_PORT=8000
else
  echo "> No WAS is connected to nginx"
fi

TARGET_PID=$(lsof -Fp -i TCP:${TARGET_PORT} | grep -Po 'p[0-9]+' | grep -Po '[0-9]+')

if [ ! -z ${TARGET_PID} ]; then
  echo "> Kill WAS running at ${TARGET_PORT}."
  sudo kill ${TARGET_PID}
fi

echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar -Dserver.port=${TARGET_PORT} $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG

echo "set \$service_url http://ec2-15-165-21-12.ap-northeast-2.compute.amazonaws.com:${TARGET_PORT};" | tee /home/ubuntu/service_url.inc

sudo service nginx reload
echo "> Nginx reloaded."
