clear all 
clc
L=13#¦Û­q
N=87 #¦Û­q

h=L/N
n=[0:1:N/2]
k=2*pi/L*n
kh=2*pi/N*n
kh_true=kh
kh_D1=(2-2*cos(kh))
kh_D2=(2*cos(kh)-2)./(-1/12*2*cos(kh)-10/12)
plot(kh,kh_true.^2)
hold
plot(kh,kh_D1)
plot(kh,kh_D2,"g")
print("-djpeg",["pictured_D",'.jpg'])