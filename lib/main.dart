import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sportify/core/utils/cache_helper.dart';
import 'package:sportify/presentation/cubit/local/cubit/local_cubit.dart';
import 'package:sportify/presentation/cubit/local/states/local_states.dart';
import 'package:sportify/presentation/cubit/login/cubit/login_cubit.dart';
import 'package:sportify/presentation/cubit/onboarding/cubit/onboarding_cubit.dart';
import 'package:sportify/presentation/cubit/splash/cubit/splash_cubit.dart';
import 'package:sportify/presentation/routes.dart';
import 'core/localization/app_localizations.dart';
import 'core/utils/app_themes.dart';
import 'data/firebase/firebase_options.dart';

void main() async{
  WidgetsFlutterBinding.ensureInitialized();
  await CacheHelper.init();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  await CacheHelper.saveData(key: 'isDark', value: true);
  bool isDark = await CacheHelper.getData(key: 'isDark') ?? false;
  String lang = await CacheHelper.getData(key: 'lang') ?? 'en';
  runApp(MyApp(isDark: isDark,lang: lang,));
}

class MyApp extends StatelessWidget {
  const MyApp({super.key,required this.isDark,required this.lang});
  final bool isDark;
  final String lang;
  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      providers: [
        BlocProvider(create: (BuildContext context) => SplashCubit()),
        BlocProvider(create: (BuildContext context) => OnboardingCubit()),
        BlocProvider(create: (BuildContext context) => LoginCubit()),
        BlocProvider(create: (BuildContext context) => LocaleCubit()..changeLanguage(lang)),
      ],
    child: BlocBuilder<LocaleCubit, LocaleStates>(
      builder: (context, state) => MaterialApp.router(
        darkTheme: isDark? AppThemes.dark:AppThemes.light,
        title: 'Sportify',
        locale: state.locale,
        supportedLocales: const [
          Locale('en', 'US'),
          Locale('ar', 'SA'),
        ],
        localizationsDelegates: const [
          AppLocalizations.delegate,
        ],
        themeMode: isDark? ThemeMode.dark:ThemeMode.light,
        debugShowCheckedModeBanner: false,
        routerConfig: router,
        builder: (context, child) {
          return Directionality(
              textDirection: state.locale.languageCode == 'ar' ? TextDirection.rtl : TextDirection.ltr,
              child: child!
          );
        },
      ),
    )
    );
  }
}
