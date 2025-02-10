import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';
import 'package:sportify/core/utils/cache_helper.dart';
import 'package:sportify/presentation/routes.dart';
import '../states/onboarding_states.dart';

class OnboardingCubit extends Cubit<OnboardingStates> {
  OnboardingCubit() : super(OnboardingInitialState()){
    isDark = CacheHelper.getData(key: 'isDark')?? false;
  }

  static OnboardingCubit get(context) => BlocProvider.of(context);

  final PageController pageController = PageController();

  var currentIndex = 0;
  var isDark = false;

  final List<String> titles = [
    'Get Started with Easy Authentication',
    'Book Your Favorite Sports Court',
    'Stay Updated with Events & Tournaments',
    'Track Your Progress and Achievements',
    'Communicate with Support Anytime',
  ];

  final List<String> descriptions = [
    'Sign up or log in using your Google, Facebook, or Email account. Whether you\'re a member or an admin, you\'ll have tailored access to the features you need. Manage your profile, track your membership status, favorite sports, and activity history.',
    'Check court availability with our intuitive calendar, and reserve your favorite slots for tennis, football, swimming, and more. Payment integration ensures a seamless booking experience, so you can focus on enjoying your game.',
    'Discover upcoming sports events and tournaments with ease. Register for events, make payments, and receive instant notifications about event changes. Stay on top of the action and never miss a chance to compete!',
    'Keep track of your performance with weekly progress reports and integration with health trackers. Whether you\'re tracking your fitness achievements or sports milestones, our app provides insightful graphs to help you improve.',
    'Need assistance or have a question? Use our real-time chat feature to communicate directly with club admins. You’ll also find a helpful FAQ section and contact form for any further inquiries.',
  ];

  final List<String> images = [
    'assets/images/onboarding_1.png',
    'assets/images/onboarding_2.png',
    'assets/images/onboarding_3.png',
    'assets/images/onboarding_4.png',
    'assets/images/onboarding_5.png',
  ];

  void onPageChanged(int index) {
    currentIndex = index;
    switch(currentIndex){
      case 4: emit(OnboardingIsLastPage());
      default: emit(OnboardingOnChangePageState());
    }
  }

  void onSkip(){
    CacheHelper.saveData(key: 'isOpened', value: true);
  }

  void onChangePage() async {
    currentIndex = (pageController.page?.toInt() ??-1);
    if(state is OnboardingIsLastPage){
      CacheHelper.saveData(key: 'isOpened', value: true);
      emit(OnboardingOnNavigatePage());
    }else{
      switch (currentIndex){
        case 4 : emit(OnboardingIsLastPage());
        default : emit(OnboardingOnChangePageState());
      }
      await pageController.nextPage(duration: const Duration(milliseconds: 500), curve: Curves.fastOutSlowIn);
    }
  }
}
