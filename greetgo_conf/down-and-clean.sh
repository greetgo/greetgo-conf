#!/bin/sh

cd "$(dirname "$0")" || exit 131

docker-compose down

docker run --rm -i \
  -v "$HOME/volumes/greetgo-conf/:/volumes" \
  busybox:1.31.0 \
  find /volumes/ -maxdepth 1 -mindepth 1 -exec rm -rf {} \;

rm -rvf volumes
