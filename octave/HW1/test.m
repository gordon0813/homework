

clear all
clc

x0=[-1:0.2:1];
y0=[0.0385,0.0588,0.1,0.2,0.5,1,0.5,0.2,0.1,0.0588,0.0385];
x=linspace(-1,1);
n=11;

A=zeros(9,9);
B=zeros(9,1);

for i=1:9;
  for j=1:9;
    if i==j
      A(i,j)=((x0(i+2)-x0(i+1))-(x0(i+1)-x0(i)))/3;
    elseif i==j-1
      A(i,j)=(x0(i+1)-x0(i))/6;
    elseif i==j+1
      A(i,j)=(x0(i+2)-x0(i+1))/6
    endif
  endfor
endfor

for i=1:9;
  B(i,1)=((y0(i+2)-y0(i+1))/(x0(i+2)-x0(i+1)))-((y0(i+1)-y0(i))/(x0(i+1)-x0(i)))
  endfor;

G=A\B;

for i=1:n;
  if i==1;
    y=(G(i)/6)*((((x-x0(i)).^3)/(x0(i+1)-x0(i)))+(x-x0(i))*(x0(i+1)-x0(i)))+y0(i)*((x0(i+1)-x)/(x0(i+1)-x0(i)))+y0(i+1)*((x-x0(i))/(x0(i+1)-x0(i)));
  elseif i>=2&&i<=n-1;
    y=(G(i-1)/6)*((((x0(i+1)-x).^3)/(x0(i+1)-x0(i)))+(x0(i+1)-x)*(x0(i+1)-x0(i)))+(G(i)/6)*((((x-x0(i)).^3)/(x0(i+1)-x0(i)))+(x-x0(i))*(x0(i+1)-x0(i)))+y0(i)*((x0(i+1)-x)/(x0(i+1)-x0(i)))+y0(i+1)*((x-x0(i))/(x0(i+1)-x0(i)));
  elseif i==n;
    y=(G(i-1)/6)*((((x0(i+1)-x)^3)/(x0(i+1)-x0(i)))+(x0(i+1)-x)*(x0(i+1)-x0(i)))+y0(i)*((x0(i+1)-x)/(x0(i+1)-x0(i)))+y0(i+1)*((x-x0(i))/(x0(i+1)-x0(i)));
  endif;  
  figure(i);
  plot(x,y);
endfor;