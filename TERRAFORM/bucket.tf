terraform {
  backend "s3" {
    bucket  = "mybucket"
    encrypt = true
    key     = "../TERRAFORM/terraform.tfstate"
    region  = "ap-south-1"
    profile = "shireen_syed"
  }
}
