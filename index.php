<?php
  echo "Hello World !";
  if(file_exists("test.php")){
    echo "Fichier existant!";
  }else{
    echo "Fichier inconnu!";
  }
  include "test.php";
?>
