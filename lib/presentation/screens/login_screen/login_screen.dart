import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';
import 'package:sportify/core/utils/validators.dart';

import '../../../core/utils/app_colors.dart';
import '../../../core/widgets/custom_big_textfield.dart';
import '../../../core/widgets/custom_button.dart';
import '../../cubit/login/cubit/login_cubit.dart';
import '../../cubit/login/states/login_states.dart';
import '../../routes.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    var cubit = LoginCubit.get(context);
    return BlocConsumer<LoginCubit, LoginStates>(
        builder: (context, state) =>  Scaffold(
              body: Form(
                key: cubit.formKey,
                child:  Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 20),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisSize: MainAxisSize.max,
                    children: [
                      const Text(
                        'Sportify Club',
                        style: TextStyle(
                          fontSize: 40,
                          fontWeight: FontWeight.w700,
                          height: 1.50,
                        ),
                      ),
                      const SizedBox(height: 25),
                      CustomBigTextField(
                        observe: false,
                        label: 'Email',
                        controller: cubit.emailController,
                        isDark: false,
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Please enter your email';
                          }
                          if (!Validators.isValidEmail(value)) {
                            return 'Please enter a valid email';
                          }
                          return null;
                        },
                      ),
                      const SizedBox(height: 25),
                      CustomBigTextField(
                        observe: true,
                        label: 'Password',
                        controller: cubit.passwordController,
                        isDark: false,
                      ),
                      const SizedBox(height: 25,),
                      CustomButton(
                        height: 55,
                        onPressed: () {
                          if(cubit.formKey.currentState!.validate()){
                            cubit.login();
                          }
                        },
                        btnColor: cubit.isDark? AppColors.white:AppColors.mirage2,
                        text: 'Login',
                        isDark: cubit.isDark,
                      ),
                      const SizedBox(height: 8),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        mainAxisSize: MainAxisSize.max,
                        children: [
                          const Text(
                            'IF YOU DON\'T HAVE AN ACCOUNT',
                            style: TextStyle(
                              fontSize: 15,
                              fontWeight: FontWeight.w500,
                              height: 1.50,
                            ),
                          ),
                          const SizedBox(width: 5,),
                          GestureDetector(
                            onTap: () {

                            },
                            child: const Text(
                              'REGISTER',
                              style: TextStyle(
                                fontSize: 15,
                                fontWeight: FontWeight.w800,
                                height: 1.50,
                                decoration: TextDecoration.underline,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            ),
        listener: (context, state) {
          if(state is LoginSuccessState){
            context.replace(AppRoutePaths.home);
          }
        },
        listenWhen: (previous, current) => current is LoginSuccessState,
        buildWhen: (previous, current) => current is LoginErrorState || current is LoginLoadingState,
    );
  }
}
