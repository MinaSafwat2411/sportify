import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';

import '../../cubit/splash/cubit/splash_cubit.dart';
import '../../cubit/splash/states/splash_states.dart';
import '../../routes.dart';

class SplashScreen extends StatelessWidget {
  const SplashScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocConsumer<SplashCubit, SplashStates>(
      builder: (context, state) => const Scaffold(
        body: Center(
          child: Image(
            image:AssetImage('assets/images/sportify_logo.png'),
            height: 120,
            width: 120,
          ),
        ),
      ),
      listener: (context, state) {
        switch (state) {
          case OnNavigateToLoginScreen(): context.replace(AppRoutePaths.login);
          case OnNavigateToHomeScreen(): context.replace(AppRoutePaths.home);
          case OnNavigateToOnBoardingScreen(): context.replace(AppRoutePaths.onboarding);
        }
      },
      bloc: SplashCubit(),
    );
  }
}
