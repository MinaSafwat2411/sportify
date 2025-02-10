import 'package:flutter/material.dart';

import 'package:flutter/material.dart';

abstract class LocaleStates {
  final Locale locale;
  const LocaleStates(this.locale);
}

class LocaleInitial extends LocaleStates {
  const LocaleInitial(Locale locale) : super(locale);
}

class LocaleUpdated extends LocaleStates {
  const LocaleUpdated(Locale locale) : super(locale);
}

class LocaleError extends LocaleStates {
  const LocaleError(Locale locale) : super(locale);
}

class LocaleLoading extends LocaleStates {
   LocaleLoading() : super(Locale('en')); // Default language during loading
}
