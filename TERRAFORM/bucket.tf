terraform {
  backend "s3" {
    bucket  = "mybucket"
    encrypt = true
    key     = "../TERRAFORM/terraform.tfstate"
    region  = "ap-south-1"
    profile = "shireen__syed"
    access_key = var.access_key
    secret_key = var.secret_access_key
  }
}
