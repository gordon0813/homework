clear all 
clc
L=13 #¦Û­q
N=87 #¦Û­q

h=L/N
n=[0:1:N/2]
k=2*pi/L*n
kh=2*pi/N*n
kh_second_order=sin(kh)
kh_fourth_order=(-sin(4*pi*n/N)+8*sin(2*pi*n/N))/6
kh_Pade_scheme=3*sin(kh)./(2+cos(kh))

kh_true=kh
plot(kh,kh_second_order)
hold 
plot(kh,kh_true)
plot(kh,kh_fourth_order)   
plot(kh,kh_Pade_scheme)
print("-djpeg",["pictured_B",'.jpg'])
