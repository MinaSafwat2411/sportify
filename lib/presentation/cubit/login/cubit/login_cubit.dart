import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sportify/data/services/firebase_services.dart';
import 'package:sportify/presentation/cubit/login/states/login_states.dart';
import '../../../../data/models/login_model.dart';
import '../../../../data/services/cache_helper.dart';

class LoginCubit extends Cubit<LoginStates> {
  LoginCubit() : super(LoginInitialState()) {
    isDark = CacheHelper.getData(key: 'isDark') ?? false;
  }

  static LoginCubit get(context) => BlocProvider.of(context);

  var formKey = GlobalKey<FormState>();
  var emailController = TextEditingController();
  var passwordController = TextEditingController();
  var isDark = false;
  var token = '';

  void login() async {
    final firebaseServices = FirebaseServices();
    try {
      emit(LoginLoadingState());
      LoginModel login = LoginModel(
          email: emailController.text, password: passwordController.text);
      token = await firebaseServices.login(login);
      await CacheHelper.saveData(key: 'token', value: token);
      emit(LoginSuccessState());
    } catch (e) {
      emit(LoginErrorState());
    }
  }
}
