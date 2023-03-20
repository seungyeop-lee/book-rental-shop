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
      (cd $docker_compose_dir && docker-compose up backend_backoffice_book_manager --build -d)

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
      (cd $docker_compose_dir && docker-compose up backend_backoffice_rental_manager --build -d)

  # member-manager
  # build
  elif [ "$1" = "build-member-manager" ]; then
    # context/member의 하위 폴더에서 전부 go generate 실행
    for d in ./context/member/*/ ; do
      (cd "$d" && go generate ./...)
    done
    # runner/member-manager에서 go generate 실행
    (cd runner/member-manager && go generate ./...)

    # gcflags를 이용해 컴파일러 최적화 및 인라인 비활성화
    # => 빠른 빌드를 위해 옵션 추가 (go tool compile -help 참조)
    (cd runner/member-manager && GOOS=linux go build -gcflags "all=-N -l" -o build/app)
    status=$?
    if [ $status != 0 ]; then
      exit 1
    fi
  # update
  elif [ "$1" = "update-member-manager" ]; then
      (cd $docker_compose_dir && docker-compose up backend_backoffice_member_manager --build -d)

  fi
}

for command in $commands; do
  runCommand "$command"
done
