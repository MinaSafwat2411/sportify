import 'package:flutter/material.dart';

import 'app_colors.dart';

abstract class AppThemes {
  static final light = ThemeData(
    scaffoldBackgroundColor: AppColors.white,
    iconTheme: const IconThemeData(
      color: AppColors.black,
    ),
    textTheme: const TextTheme(
      bodyLarge: TextStyle(color: AppColors.mirage2),
      bodyMedium: TextStyle(color: AppColors.mirage2),
      bodySmall: TextStyle(color: AppColors.mirage2),
      displayLarge: TextStyle(color: AppColors.mirage2),
      displayMedium: TextStyle(color: AppColors.mirage2),
      displaySmall: TextStyle(color: AppColors.mirage2),
      headlineLarge: TextStyle(color: AppColors.mirage2),
      headlineMedium: TextStyle(color: AppColors.mirage2),
      headlineSmall: TextStyle(color: AppColors.mirage2),
      labelLarge: TextStyle(color: AppColors.mirage2),
      labelMedium: TextStyle(color: AppColors.mirage2),
      labelSmall: TextStyle(color: AppColors.mirage2),
      titleLarge: TextStyle(color: AppColors.mirage2),
      titleMedium: TextStyle(color: AppColors.mirage2),
      titleSmall: TextStyle(color: AppColors.mirage2),
    ),
    textButtonTheme: TextButtonThemeData(
      style: ButtonStyle(
        foregroundColor: WidgetStateProperty.all(AppColors.white), // Set text color
      ),
    ),
  );
  static final dark = ThemeData(
    scaffoldBackgroundColor: AppColors.mirage2,
    iconTheme: const IconThemeData(
      color: AppColors.white,
    ),
    textTheme: const TextTheme(
      bodyLarge: TextStyle(color: AppColors.white),
      bodyMedium: TextStyle(color: AppColors.white),
      bodySmall: TextStyle(color: AppColors.white),
      displayLarge: TextStyle(color: AppColors.white),
      displayMedium: TextStyle(color: AppColors.white),
      displaySmall: TextStyle(color: AppColors.white),
      titleSmall: TextStyle(color: AppColors.white),
      titleMedium: TextStyle(color: AppColors.white),
      titleLarge: TextStyle(color: AppColors.white),
      labelSmall: TextStyle(color: AppColors.white),
      labelMedium: TextStyle(color: AppColors.white),
      labelLarge: TextStyle(color: AppColors.white),
      headlineSmall: TextStyle(color: AppColors.white),
      headlineMedium: TextStyle(color: AppColors.white),
      headlineLarge: TextStyle(color: AppColors.white),
    ),
    textButtonTheme: TextButtonThemeData(
      style: ButtonStyle(
        foregroundColor: WidgetStateProperty.all(AppColors.white), // Set text color
      ),
    ),
  );
}