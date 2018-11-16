<?php

// autoload_static.php @generated by Composer

namespace Composer\Autoload;

class ComposerStaticInit7c5881162f2ff99e55ffa85529ec8145
{
    public static $prefixLengthsPsr4 = array (
        'P' => 
        array (
            'PokePHP\\' => 8,
        ),
    );

    public static $prefixDirsPsr4 = array (
        'PokePHP\\' => 
        array (
            0 => __DIR__ . '/..' . '/danrovito/pokephp/src',
        ),
    );

    public static function getInitializer(ClassLoader $loader)
    {
        return \Closure::bind(function () use ($loader) {
            $loader->prefixLengthsPsr4 = ComposerStaticInit7c5881162f2ff99e55ffa85529ec8145::$prefixLengthsPsr4;
            $loader->prefixDirsPsr4 = ComposerStaticInit7c5881162f2ff99e55ffa85529ec8145::$prefixDirsPsr4;

        }, null, ClassLoader::class);
    }
}