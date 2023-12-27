import serial, time
from firebase import firebase

firebase = firebase.FirebaseApplication('https://prueba-aplicaciones-2-default-rtdb.firebaseio.com/')


arduino = serial.Serial('COM6', 9600)

time.sleep(2)

while True:
    dato = arduino.readline().decode('utf-8')
    data = {'dato': dato}
    print(dato)
    result = firebase.put('datos/Temp1', 'Temperatura', dato)