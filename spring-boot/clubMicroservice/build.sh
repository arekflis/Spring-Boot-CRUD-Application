title="$(grep -n "org.opencontainers.image.title" Dockerfile | cut -f2 -d "=" | xargs)"

version="$(grep -n "org.opencontainers.image.version" Dockerfile | cut -f2 -d "=" | xargs)"

docker build \
    --label "org.opencontainers.image.created=$(date +%Y-%m-%dT%H:%M:%S%:z)" \
    -t "${title}":"${version}" .