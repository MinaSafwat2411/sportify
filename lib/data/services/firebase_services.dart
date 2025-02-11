import 'package:firebase_auth/firebase_auth.dart';
import 'package:sportify/data/models/login_model.dart';

class FirebaseServices {
  final firebaseAuth = FirebaseAuth.instance;

  Future<String> login(LoginModel login)async{
    final response = await firebaseAuth.signInWithEmailAndPassword(email: login.email, password: login.password);
    return response.user?.uid?? '';
  }
}