<?php
/*  echo "Hello World !";

  $method = "POST";
  $url = "https://pokeapi.co/api/v2/pokedex/1/";
  //$returnData = CallAPI($method, $url);
  include_once("https://pokeapi.co/api/v2/pokedex/1/");

  echo "<pre>";
  print_r($returnData);
  echo "</pre>";
  echo "<br/>";

  function CallAPI($method, $url, $data = false)
  {
      $curl = curl_init();

      switch ($method)
      {
          case "POST":
              curl_setopt($curl, CURLOPT_POST, 1);

              if ($data)
                  curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
              break;
          case "PUT":
              curl_setopt($curl, CURLOPT_PUT, 1);
              break;
          default:
              if ($data)
                  $url = sprintf("%s?%s", $url, http_build_query($data));
      }

      // Optional Authentication:
      curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
      curl_setopt($curl, CURLOPT_USERPWD, "username:password");

      curl_setopt($curl, CURLOPT_URL, $url);
      curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);

      $result = curl_exec($curl);

      curl_close($curl);

      return $result;
  }*/
  use PokePHP\PokeApi;
  $api = new PokeApi();
  die();
  echo "jlkj";
  echo $api->pokemon(10);
  /*$pokemon = $api->pokemon(10);
  echo "<pre>";
    echo $pokemon;
  echo "</pre>";*/
?>
