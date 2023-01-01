#!/bin/bash

commands=$*
docker_compose_dir="../../docker-compose"

# OS 별 gradlew 실행 파일 설정
OS="$(uname)"
case $OS in
Linux*)
  OS='Linux'
  gradlew="./gradlew"
  ;;
WindowsNT*)
  OS='Windows'
  gradlew="./gradlew.bat"
  ;;
Darwin*)
  OS='Mac'
  gradlew="./gradlew"
  ;;
MINGW*)
  OS='MINGW'
  gradlew="./gradlew.bat"
  ;;
*) ;;
esac

function runCommand() {
  # rmi
  if [ "$1" = "rmi" ]; then
    imageList=$(docker images -f "dangling=true" -q)

    if [ "$imageList" != "" ]; then
      docker rmi $imageList
    fi

  # book-manager
  # build
  elif [ "$1" = "build-book-manager" ]; then
    $gradlew :runner:book-manager:build -x test
    status=$?
    if [ $status != 0 ]; then
      exit 1
    fi
  # update
  elif [ "$1" = "update-book-manager" ]; then
      (cd $docker_compose_dir && docker compose up backend_backoffice_book_manager --build -d)

  # rental-manager
  # build
  elif [ "$1" = "build-rental-manager" ]; then
    $gradlew :runner:rental-manager:build -x test
    status=$?
    if [ $status != 0 ]; then
      exit 1
    fi
  # update
  elif [ "$1" = "update-rental-manager" ]; then
      (cd $docker_compose_dir && docker compose up backend_backoffice_rental_manager --build -d)

  fi
}

for command in $commands; do
  runCommand "$command"
done
