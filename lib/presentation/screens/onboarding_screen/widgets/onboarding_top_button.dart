import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

import '../../../routes.dart';

class OnboardingTopButton extends StatelessWidget {
  const OnboardingTopButton({super.key,required this.text,required this.isDark,required this.function});

  final String text;
  final bool isDark;
  final GestureTapCallback function;

  @override
  Widget build(BuildContext context) {
    return  Padding(
      padding: const EdgeInsets.only(top: 33,right: 20,bottom: 50),
      child: GestureDetector(
        onTap:function,
          child: Text(text,style: const TextStyle(fontSize:  20,fontWeight: FontWeight.w600),)));
  }
}
