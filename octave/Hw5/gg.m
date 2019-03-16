clear all 
clc
#################change input function
function re=f(l) 
  x1=[-l:0.001:0];
  x2=[0:0.001:l];
  x=[x1,x2];
  y=[x1+l/2,-x2+l/2];  
  #y=[x2.^4,x1.^4];
  #y=[x1+l/2,x2+l/2];
  #y=exp(-[x1,x2])+2;
  #y=sin(4*x)+2*cos(2*x);
  re=[[x1,x2];y];
end
#################
################change Period
L=pi;
################
########################## change m c k
m=1;
c=0.05;
k=24;
########################## to nth of fourier series 
to_n=30
#########################
yy=f(L)(2,:);
xx=f(L)(1,:);
function re = intergal(x,y)
  re=sum(y)*(x(end)-x(1))/size(x)(2);
end
yyy=intergal(xx,yy);
a0=intergal(xx,yy)/(2*L);
aa=[];
ba=[];
for i=1:to_n+2
  aa(i)=intergal(xx,yy.*cos(i*pi/L*xx))/L;
  ba(i)=intergal(xx,yy.*sin(i*pi/L*xx))/L;
end
##########################################^^about ft^^


x1=[-pi:0.01:0];
x2=[0:0.01:pi];

yinput=yy;

x=xx;
figure(1);
plot(x,yinput/10,"b")   #divide by 10
hold;





 i=1
 n=i*2-1
 
 f0=aa(1);
 w=n;
 a=f0*((k-m*w^2)/((k-m*w^2)^2+w^2*c^2));
 b=f0*(w*c/((k-m*w^2)^2+w^2*c^2));
 yout=a*cos(n*x)+b*sin(n*x);
 fy=aa(1)*cos(x)+a0+ba(1)*sin(x);
 
for i=2:to_n     
  n=i;
  f0=aa(n);
  w=n;
  D=(k-m*n^2)^2+(c*n)^2;
  a=f0*((k-m*w^2)/D);
  b=f0*(w*c/D);
  yout=yout+a*cos(n*x)+b*sin(n*x);
  fy=fy+aa(i)*cos(i*x)+ba(i)*sin(i*x);
end

plot(x,yout);
#figure(2)
#plot([x,x+2*L,x+4*L,x+6*L],[yinput,yinput,yinput,yinput]);
#hold
#plot([x,x+2*L,x+4*L,x+6*L],[fy,fy,fy,fy]);
