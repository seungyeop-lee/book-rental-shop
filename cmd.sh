#!/bin/bash

commands=$*
docker_compose_dir="docker-compose"

function runCommand() {
  # init
  if [ "$1" = "init" ]; then
    (cd frontend/backoffice && bash cmd.sh init)
    go install github.com/golang/mock/mockgen@v1.6.0

  # up
  elif [ "$1" = "up" ]; then
    (cd backend/backoffice && bash cmd.sh build-book-manager)
    (cd backend/backoffice && bash cmd.sh build-rental-manager)
    (cd backend/backoffice && bash cmd.sh build-member-manager)
    (cd frontend/backoffice && bash cmd.sh build)
    (cd $docker_compose_dir && docker-compose up -d --build)

  # down
  elif [ "$1" = "down" ]; then
    (cd $docker_compose_dir && docker-compose down)

  # rmi
  elif [ "$1" = "rmi" ]; then
    imageList=$(docker images -f "dangling=true" -q)

    if [ "$imageList" != "" ]; then
      docker rmi $imageList
    fi

  # log
  elif [ "$1" = "log" ]; then
      (cd $docker_compose_dir && docker-compose logs -f)
  fi
}

for command in $commands; do
  runCommand "$command"
done
