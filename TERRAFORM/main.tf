provider "aws" {
  region    = var.aws_region
  access_key = var.AWS_ACCESS_KEY
  secret_key = var.AWS_SECRET_KEY
  profile = "shireen_syed"
}

resource "tls_private_key" "pk" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "aws_s3_bucket" "employeeportalbucket" {
  bucket_prefix = var.bucket_prefix
  acl = var.acl
  
   versioning {
    enabled = var.versioning
  }
  
  tags = var.tags
}
resource "aws_key_pair" "employeeportalsecretkey" {
  key_name   = "employeeportalsecretkey" # Create a "myKey" to AWS!!
  public_key = var.public_key
  provisioner "local-exec" {
    command = "echo '${tls_private_key.pk.private_key_pem}' > ./employeeportalsecretkey.pem"
  }
}

#Create security group with firewall rules
resource "aws_security_group" "security_port" {
  name        = "security_port"
  description = "security group "

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # outbound from jenkis server
  egress {
    from_port   = 0
    to_port     = 65535
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "security_port"
  }


}

resource "aws_instance" "employeePortal" {
  ami             = "ami-0756a1c858554433e"
  key_name        = "employeeportalsecretkey"
  instance_type   = var.instance_type
  security_groups = ["security_port"]
  tags = {
    Name = "employeeportal_ec2_instance"
  }

  connection {
    type        = "ssh"
    host        = self.public_ip
    user        = "ubuntu"
    private_key = var.private_key
    timeout     = "4m"
  }
}

 terraform {
  backend "s3" {
    bucket = "employeeportalbuckett"
    key    = "terraform.tfstate"
    region = "ap-south-1"
    profile = "shireen_syed"
  }
}
 
