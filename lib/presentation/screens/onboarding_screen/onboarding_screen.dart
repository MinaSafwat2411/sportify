import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';
import 'package:sportify/presentation/screens/onboarding_screen/widgets/onboarding_bottom_button.dart';
import 'package:sportify/presentation/screens/onboarding_screen/widgets/onboarding_item.dart';
import 'package:sportify/presentation/screens/onboarding_screen/widgets/onboarding_top_button.dart';

import '../../cubit/onboarding/cubit/onboarding_cubit.dart';
import '../../cubit/onboarding/states/onboarding_states.dart';
import '../../routes.dart';

class OnboardingScreen extends StatelessWidget {
  const OnboardingScreen({super.key});

  @override
  Widget build(BuildContext context) {
    var cubit = OnboardingCubit.get(context);
    return BlocConsumer<OnboardingCubit, OnboardingStates>(
      listener: (context, state) {
        if(state is OnboardingOnNavigatePage){
          context.replace(AppRoutePaths.login);
        }
      },
      listenWhen: (previous, current) => current is OnboardingOnNavigatePage,
      buildWhen: (previous, current) => current is OnboardingIsLastPage||current is OnboardingOnChangePageState,
      builder: (context, state) {
        return Scaffold(
          body: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.end,
            children: [
              OnboardingTopButton(
                function: () {
                  cubit.onSkip();
                  context.replace(AppRoutePaths.login);
                },
                text: 'Skip',
                isDark: cubit.isDark,
              ),
              Expanded(
                child: PageView(
                  onPageChanged: (value) {
                    cubit.onPageChanged(value);
                  },
                  controller: cubit.pageController,
                  children: List.generate(
                    cubit.titles.length,
                    (index) => OnboardingItem(
                      title: cubit.titles[index],
                      description: cubit.descriptions[index],
                      image: cubit.images[index],
                    ),
                  ),
                ),
              ),
              OnboardingBottomButton(
                pageController: cubit.pageController,
                title: state is OnboardingIsLastPage
                    ? 'Let\'s Go'
                    : 'Next',
                function: () => cubit.onChangePage(),
                isDark: cubit.isDark,
              ),
            ],
          ),
        );
      },
    );
  }
}
