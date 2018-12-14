clear all
clc

function re=u(x)
  re=4*(x.^2-x.^4).*exp(-x/2)
endfunction
function re=firstd(x)
  re=4*(exp(-x/2).*(2*x-4*x.^3)-1/2*exp(-x/2).*(x.^2-x.^4))
endfunction
function re=secd(x)
  re=-47*exp(-x/2).*x.^2   -exp(-x/2).*x.^4+16*exp(-x/2).*x.^3-8*exp(-x/2).*x+8*exp(-x/2)
endfunction
d=[]
n=8
x=[0:1:n-1]
x=cos(x/(n-1)*pi)
#x=[1,0.809,0.309,-0.309,-0.809,-1]
N=n-1
cj=1
ck=1
for j =1:n
  cj=1
  if(j==1 || j==n)
    cj=2
  endif
  for k =1:n
    ck=1
    
    if(k==1 || k==n)
      ck=2
    endif
    if(j!=k)
      d(j,k)=cj*(-1)^(j+k-2)/(ck*(x(1,j)-x(1,k)))
    elseif (j==k && j==1)
      d(j,k)=(2*N^2+1)/6
    elseif (j==k && j==n)
      d(j,k)=-(2*N^2+1)/6
    else
      d(j,k)=-x(1,j)/(2*(1-x(1,j)^2))
    endif
  end
end
u1=u(x)
t1=rot90(u1,-1)
yd=d*rot90(u1,-1)
figure(1)
plot(x,yd,"*")
xreal=linspace(-1,1,33)
fd=firstd(xreal)

hold
plot(xreal,fd,"k")

ydd=d*(d*t1)
figure(2)
plot(x,ydd,"*")
hold
plot(xreal,secd(xreal))