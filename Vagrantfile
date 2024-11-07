# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  config.vm.box = "debian/bookworm64"
  config.vm.synced_folder "docker/", "/docker"
  config.vm.provision "shell",
			inline: "apt-get update && 
			apt-get -y install net-tools dnsutils docker.io docker-compose maven &&
			cd /vagrant && mvn clean package &&
			sudo usermod -aG docker vagrant &&
			echo {\"registry-mirrors\": [\"http://172.18.48.9:5000\"],\"default-address-pools\":[{\"base\":\"172.20.0.0/16\",\"size\":24}]} > /etc/docker/daemon.json"

  config.vm.network "forwarded_port", guest: 8080, host: 8080

end
