import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import '../../../../core/utils/cache_helper.dart';
import '../states/splash_states.dart';


class SplashCubit extends Cubit<SplashStates>{

  SplashCubit (): super(SplashInitialState()){
    isOpened = CacheHelper.getData(key: 'isOpened')?? false;
    token =CacheHelper.getData(key: 'token')?? '';
    onNavigate();
  }

  var isOpened = false;
  var token = '';
  static SplashCubit get(context) => BlocProvider.of(context);


  void onNavigate(){
    Timer(const Duration(seconds: 5), () {
      if(isOpened) {
        if(token.isEmpty){
          emit(OnNavigateToLoginScreen());
        }else{
          emit(OnNavigateToHomeScreen());
        }
      } else {
        emit(OnNavigateToOnBoardingScreen());
      }
    });
  }
}