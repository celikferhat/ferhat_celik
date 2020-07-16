# Network Monitoring


![Product Gif](src/assets/github/modem.png)

ÖZET 

	Bu bitirme çalışması G.T.U. Bilgisayar Mühendisliği bölümü 4. Sınıfda hazırlanacak Lisans Bitirme Projesi içeriğini, projenin yapılma nedenini ve amacını, projenin gereksinimlerini, sistem mimarisini, başarı kriterlerini, kullanılacak olan donanım hakkında bilgiler, donanım fonksiyonları ve donanımın nasıl kullanılacağı hakkında bilgiler içermektedir.

	Bu projede yerel ağın izlenebilmesini , ağda bulunan cihazların bulunmasını , tanımlanmasını ve kategorize edilmesini sağlayan bir ağ görüntüleme sistemi tasarlanmıştır. Ağ izleme yazılımlarıyla Router, Server, switch ve diğer önemli network cihazları veya uygulamalar gibi belirlenmiş hostlardan istatikler toplanır, Network trafiği detayı, CPU değerleri, disk kullanımı, Up-Down durumu gibi önemli verileri kontrol edilebilir. 
	Ağ cihazlarını keşfetme ve ip , mac gibi basit bilgileri edinmek için Nmap kullanılmıştır.Nmap ile alınan veriler düzenlenerek parametre olarak Fing’e gönderilir ve detaylı cihaz bilgileri mac adresleri sayesinde öğrenilir ve kategorize edilir.Python üzerinde flask ile yazılmış web servis aracılığıyla arayüzü yazmak için kullanılan React ‘a iletilir.Arayüz üzerinde seçilen cihaza ait daha detaylı bilgilere SNMP aracılığıyla ulaşılır.
	
 
SUMMARY
	This graduation work is made by G.T.U. The Computer Engineering department contains the content of the Undergraduate Completion Project to be prepared in the 4th grade, the reason and purpose of the project, the requirements of the project, the system architecture, the criteria of success, information about the hardware to be used, hardware functions and how to use the hardware.
In this project, a network imaging system is designed that allows the local network to be monitored, the devices found on the network to be identified, identified and categorized. With network monitoring software, statistics are collected from designated hosts such as Router, Server, switch and other important network devices or applications. Important data such as network traffic details, CPU values, disk usage, Up-Down status can be checked.
Nmap is used to discover network devices and to obtain simple information such as ip and mac. The data received with Nmap is edited and sent to Fing as a parameter, and detailed device information is learned and categorized through mac addresses. Used to write the interface via flask written web service on Python. It is transmitted to React. More detailed information about the device selected on the interface is accessed via SNMP. 


## Table of Contents

* [Versions](#versions)
* [Demo](#demo)
* [Quick Start](#quick-start)
* [Documentation](#documentation)
* [File Structure](#file-structure)
* [Browser Support](#browser-support)
* [Resources](#resources)
* [Reporting Issues](#reporting-issues)
* [Technical Support or Questions](#technical-support-or-questions)
* [Licensing](#licensing)
* [Useful Links](#useful-links)


## Versions

[<img src="src/assets/github/html.png" width="60" height="60" />](https://www.creative-tim.com/product/material-dashboard)
[<img src="src/assets/github/react.svg" width="60" height="60" />](https://www.creative-tim.com/product/material-dashboard-react)
[<img src="src/assets/github/vuejs.png" width="60" height="60" />](https://www.creative-tim.com/product/vue-material-dashboard)
[<img src="src/assets/github/angular.png" width="60" height="60" />](https://www.creative-tim.com/product/material-dashboard-angular2)


| HTML | React | Vue | Angular |
| --- | --- | --- | --- |
| [![Material Dashboard HTML](src/assets/github/opt_md_thumbnail.jpg)](https://www.creative-tim.com/product/material-dashboard) | [![Material Dashboard React](src/assets/github/opt_mdr_thumbnail.jpg)](https://www.creative-tim.com/product/material-dashboard-react) | [![Vue Material Dashboard ](src/assets/github/opt_md_vue_thumbnail.jpg)](https://www.creative-tim.com/product/vue-material-dashboard) | [![Material Dashboard Angular](src/assets/github/opt_md_angular_thumbnail.jpg)](https://www.creative-tim.com/product/material-dashboard-angular2)

## Demo

| Dashboard | User Profile | Tables | Maps | Notification |
| --- | --- | --- | --- | --- |
| [![Start page](src/assets/github/dashboard.png)](https://demos.creative-tim.com/material-dashboard-react/#/dashboard) | [![User profile page](src/assets/github/user_profile.png)](https://demos.creative-tim.com/material-dashboard-react/#/user) | [![Tables page ](src/assets/github/tables.png)](https://demos.creative-tim.com/material-dashboard-react/#/table) | [![Maps Page](src/assets/github/maps.png)](https://demos.creative-tim.com/material-dashboard-react/#/maps) | [![Notification page](src/assets/github/notification.png)](https://demos.creative-tim.com/material-dashboard-react/#/notifications)

[View More](https://demos.creative-tim.com/material-dashboard-react/#/dashboard).


## Quick start

Quick start options:

- `npm i material-dashboard-react`
- Clone the repo: `git clone https://github.com/creativetimofficial/material-dashboard-react.git`.
- [Download from Github](https://github.com/creativetimofficial/material-dashboard-react/archive/master.zip).
- [Download from Creative Tim](https://www.creative-tim.com/product/material-dashboard-react).


## Documentation
The documentation for the Material Dashboard React is hosted at our [website](https://demos.creative-tim.com/material-dashboard-react/#/documentation/tutorial).


## File Structure

Within the download you'll find the following directories and files:

```
material-dashboard-react
.
├── CHANGELOG.md
├── ISSUE_TEMPLATE.md
├── LICENSE.md
├── README.md
├── bower.json
├── gulpfile.js
├── jsconfig.json
├── package.json
├── documentation
│   ├── assets
│   │   ├── css
│   │   ├── img
│   │   │   └── faces
│   │   └── js
│   └── tutorial-components.html
├── public
│   ├── favicon.ico
│   ├── index.html
│   └── manifest.json
└── src
    ├── index.js
    ├── logo.svg
    ├── routes.js
    ├── assets
    │   ├── css
    │   │   └── material-dashboard-react.css
    │   ├── github
    │   │   ├── md-react.gif
    │   │   └── react.svg
    │   ├── img
    │   │   └── faces
    │   └── jss
    │       ├── material-dashboard-react
    │       │   ├── components
    │       │   ├── layouts
    │       │   └── views
    │       └── material-dashboard-react.js
    ├── components
    │   ├── Card
    │   │   ├── Card.js
    │   │   ├── CardAvatar.js
    │   │   ├── CardBody.js
    │   │   ├── CardFooter.js
    │   │   ├── CardHeader.js
    │   │   └── CardIcon.js
    │   ├── CustomButtons
    │   │   └── Button.js
    │   ├── CustomInput
    │   │   └── CustomInput.js
    │   ├── CustomTabs
    │   │   └── CustomTabs.js
    │   ├── FixedPlugin
    │   │   └── FixedPlugin.js
    │   ├── Footer
    │   │   └── Footer.js
    │   ├── Grid
    │   │   ├── GridContainer.js
    │   │   └── GridItem.js
    │   ├── Navbars
    │   │   ├── AdminNavbarLinks.js
    │   │   ├── Navbar.js
    │   │   └── RTLNavbarLinks.js
    │   ├── Sidebar
    │   │   └── Sidebar.js
    │   ├── Snackbar
    │   │   ├── Snackbar.js
    │   │   └── SnackbarContent.js
    │   ├── Table
    │   │   └── Table.js
    │   ├── Tasks
    │   │   └── Tasks.js
    │   └── Typography
    │       ├── Danger.js
    │       ├── Info.js
    │       ├── Muted.js
    │       ├── Primary.js
    │       ├── Quote.js
    │       ├── Success.js
    │       └── Warning.js
    ├── layouts
    │   ├── Admin.js
    │   └── RTL.js
    ├── variables
    │   ├── charts.js
    │   └── general.js
    └── views
        ├── Dashboard
        │   └── Dashboard.js
        ├── Icons
        │   └── Icons.js
        ├── Maps
        │   └── Maps.js
        ├── Notifications
        │   └── Notifications.js
        ├── RTLPage
        │   └── RTLPage.js
        ├── TableList
        │   └── TableList.js
        ├── Typography
        │   └── Typography.js
        ├── UpgradeToPro
        │   └── UpgradeToPro.js
        └── UserProfile
            └── UserProfile.js
```

## Browser Support

At present, we officially aim to support the last two versions of the following browsers:

<img src="src/assets/github/chrome.png" width="64" height="64"> <img src="src/assets/github/firefox.png" width="64" height="64"> <img src="src/assets/github/edge.png" width="64" height="64"> <img src="src/assets/github/safari.png" width="64" height="64"> <img src="src/assets/github/opera.png" width="64" height="64">


## Resources
- Demo: https://demos.creative-tim.com/material-dashboard-react
- Download Page: https://www.creative-tim.com/product/material-dashboard-react
- Documentation: https://demos.creative-tim.com/material-dashboard-react/#/documentation/tutorial
- License Agreement: https://www.creative-tim.com/license
- Support: https://www.creative-tim.com/contact-us
- Issues: [Github Issues Page](https://github.com/creativetimofficial/material-dashboard-react/issues)
- [Material Kit React - For Front End Development](https://www.creative-tim.com/product/material-kit-react?ref=github-mdr-free)

## Reporting Issues
We use GitHub Issues as the official bug tracker for the Material Dashboard React. Here are some advices for our users that want to report an issue:

1. Make sure that you are using the latest version of the Material Dashboard React. Check the CHANGELOG from your dashboard on our [website](https://www.creative-tim.com/).
2. Providing us reproducible steps for the issue will shorten the time it takes for it to be fixed.
3. Some issues may be browser specific, so specifying in what browser you encountered the issue might help.

## Technical Support or Questions

If you have questions or need help integrating the product please [contact us](https://www.creative-tim.com/contact-us) instead of opening an issue.

## Licensing

- Copyright 2018 Creative Tim (https://www.creative-tim.com)
- Licensed under MIT (https://github.com/creativetimofficial/material-dashboard-react/blob/master/LICENSE.md)

## Useful Links

More products from Creative Tim: <https://www.creative-tim.com/products>

Tutorials: <https://www.youtube.com/channel/UCVyTG4sCw-rOvB9oHkzZD1w>

Freebies: <https://www.creative-tim.com/products>

Affiliate Program (earn money): <https://www.creative-tim.com/affiliates/new>

Social Media:

Twitter: <https://twitter.com/CreativeTim>

Facebook: <https://www.facebook.com/CreativeTim>

Dribbble: <https://dribbble.com/creativetim>

Google+: <https://plus.google.com/+CreativetimPage>

Instagram: <https://instagram.com/creativetimofficial>
