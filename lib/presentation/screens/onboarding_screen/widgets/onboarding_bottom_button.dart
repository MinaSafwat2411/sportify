import 'package:flutter/material.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';

import '../../../../core/utils/app_colors.dart';

class OnboardingBottomButton extends StatelessWidget {
  const OnboardingBottomButton({
    super.key,
    required this.pageController,
    required this.title,
    required this.function,
    required this.isDark
  });

  final PageController pageController;
  final String title;
  final GestureTapCallback function;
  final bool isDark;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 20,vertical: 40),
      child: Row(mainAxisAlignment: MainAxisAlignment.spaceBetween,children: [
        SmoothPageIndicator(controller: pageController, count: 5,effect: ExpandingDotsEffect(
          dotHeight: 24,
          dotWidth: 24,
          activeDotColor: isDark? AppColors.white:AppColors.mirage2,
          dotColor: isDark? AppColors.white:AppColors.mirage2
        ),),
        GestureDetector(
          onTap: function,
          child:  Row(
            children: [
              Text(title,style: const TextStyle(fontSize: 24,fontWeight: FontWeight.w500),),
              const SizedBox(width: 8,),
              const Icon(Icons.keyboard_double_arrow_right_outlined,size: 38,)
            ],
          ),
        )
      ],),
    );
  }
}
