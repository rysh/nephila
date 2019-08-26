FROM amazoncorretto:8u202
MAINTAINER rysh <rysh.cact@gmail.com>
RUN echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list && \
  sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 && \
  sudo apt-get update && \
  sudo apt-get install -y sbt && \
  cd tmp && \
  git clone https://github.com/rysh/nephila.git && \
  cd nephila/e2e/ && \
  sbt update
CMD echo "now running..."