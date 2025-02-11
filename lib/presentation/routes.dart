import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:sportify/presentation/screens/login_screen/login_screen.dart';
import 'package:sportify/presentation/screens/onboarding_screen/onboarding_screen.dart';
import 'package:sportify/presentation/screens/splash_screen/splash_screen.dart';

enum AppRoutes {
  login,
  splash,
  home,
  onboarding,
}

final GoRouter router = GoRouter(
  initialLocation: AppRoutePaths.splash,  // Define initial route
  routes: [
    GoRoute(path: AppRoutePaths.login, name: AppRoutes.login.name, builder: (context, state) => const LoginScreen()),
    GoRoute(path: AppRoutePaths.splash, name: AppRoutes.splash.name, builder: (context, state) => const SplashScreen()),
    GoRoute(path: AppRoutePaths.home, name: AppRoutes.home.name, builder: (context, state) => const Text('home')),
    GoRoute(path: AppRoutePaths.onboarding, name: AppRoutes.onboarding.name, builder: (context, state) => const OnboardingScreen()),
  ],
);

abstract class AppRoutePaths {
  static const String login = '/login';
  static const String splash = '/splash';
  static const String home = '/home';
  static const String onboarding = '/onboarding';
}
