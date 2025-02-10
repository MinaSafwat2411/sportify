import 'package:flutter/material.dart';

import '../utils/app_colors.dart';

class CustomButton extends StatelessWidget {
  const CustomButton(
      {super.key,
      required this.onPressed,
      required this.text,
      required this.isDark,
      required this.btnColor,
      required this.height});
  final VoidCallback onPressed;
  final String text;
  final Color btnColor;
  final double height;
  final bool isDark ;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: height,
      width: double.infinity,
      child: MaterialButton(
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
        onPressed: onPressed,
        color: btnColor,
        child: Text(
          text,
          style:  TextStyle(
            fontSize: 16,
            fontWeight: FontWeight.w500,
            color: isDark? AppColors.mirage2:AppColors.white
          ),
        ),
      ),
    );
  }
}
