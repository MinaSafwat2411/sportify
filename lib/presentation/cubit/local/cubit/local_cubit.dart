import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter/material.dart';

import '../../../../data/services/cache_helper.dart';
import '../states/local_states.dart';

class LocaleCubit extends Cubit<LocaleStates> {
  LocaleCubit() : super(const LocaleInitial(Locale('en'))) {
    _loadSavedLanguage();
  }

  void _loadSavedLanguage() async {
    String? langCode = await CacheHelper.getData(key: 'lang');
    if (langCode != null) {
      emit(LocaleUpdated(Locale(langCode)));
    }
  }

  void changeLanguage(String languageCode) async {
    try {
      emit(LocaleLoading());
      await Future.delayed(const Duration(milliseconds: 500)); // Simulate delay
      await CacheHelper.saveData(key: 'lang', value: languageCode);
      emit(LocaleUpdated(Locale(languageCode)));
    } catch (e) {
      emit(const LocaleError(Locale('en'))); // Default fallback
    }
  }
}
