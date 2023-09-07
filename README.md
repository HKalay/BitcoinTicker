# BitcoinTicker 

A Simple Cryptocurrency Price Tracker App

## Introduction

Bitcoin Ticker is a simple and user-friendly cryptocurrency price tracking app. It allows users to search for cryptocurrency information, view real-time prices, and manage their favorite coins.

## Table of Contents
- [Introduction](#introduction)
- [Core Features](#core-features)
  - [Coin Searching](#coin-searching)
  - [Real-time Coin Price Tracking](#real-time-coin-price-tracking)
  - [Firebase Authentication](#firebase-authentication)
  - [My Coins Page and Cloud Firestore](#my-coins-page-and-cloud-firestore)
- [Nice-to-Have Features](#nice-to-have-features)
  - [Background Coin Price Tracking](#background-coin-price-tracking)
  - [Internal Notification](#internal-notification)
- [Positive for Assessment](#positive-for-assessment)
- [Musts of Implementation](#musts-of-implementation)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Bitcoin Ticker is a simple and user-friendly cryptocurrency price tracking app. It allows users to search for cryptocurrency information, view real-time prices, and manage their favorite coins.

## Core Features

### Coin Searching

The app enables users to search for cryptocurrencies by name or symbol. It uses a proper database query to perform efficient searches.

### Real-time Coin Price Tracking

When users click on a coin in the search result list, they are directed to a detail screen containing the following information:
- Hashing algorithm
- Description (if available)
- Image
- Current price
- Price change percentage over the last 24 hours
- Input field to define the refresh interval
- Button to add the coin to the favorites list

The app uses a handler to refresh the current price, ensuring alignment with the activity lifecycle to prevent memory leaks.

### Firebase Authentication

Bitcoin Ticker offers simple Firebase authentication using email for user registration and login.

### My Coins Page and Cloud Firestore

Users can add their favorite coins to the Cloud Firestore database by clicking the "Add to Favorites" button on the detail screen. These favorite coins can then be accessed on the "My Coins" page.

## Nice-to-Have Features

### Background Coin Price Tracking

The app includes an Android service that performs network calls to refresh current prices when the app is in the background.

### Internal Notification

Bitcoin Ticker sends notifications if there are price changes in the favorite coins, keeping users informed about market fluctuations.

## Positive for Assessment

- The UI design is left to your creativity, but a user-friendly and aesthetically pleasing UI is highly encouraged.
- Utilize well-known libraries such as Retrofit, Dagger, Glide, etc.
- Implement smooth animations for screen transitions.
- Use recognized design patterns (Builder, Factory, Proxy, Actor, etc.).

## Musts of Implementation

- Developed in Kotlin.
- Implement dependency injection (e.g., Dagger, Kodein).
- Follow a proper software design architecture, such as MVVM (Model-View-ViewModel).
- Use a version control system (Git) and provide a well-structured README.md.
- Maintain high-quality code.
- Ensure the master branch always contains the latest version of the code.

## Installation

Clone this repository to your local machine using Git:

```bash
git clone https://github.com/yourusername/bitcoin-ticker.git

