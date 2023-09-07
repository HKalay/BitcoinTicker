# BitcoinTicker 

A Simple Cryptocurrency Price Tracker App

## Table of Contents
- [Introduction](#introduction)
- [Core Features](#core-features)
  - [Coin Searching](#coin-searching)
  - [Real-time Coin Price Tracking](#real-time-coin-price-tracking)
  - [Firebase Authentication](#firebase-authentication)
  - [Favorites Page and Cloud Firestore](#my-coins-page-and-cloud-firestore)
  - [Background Coin Price Tracking](#background-coin-price-tracking)
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

### Firebase Authentication

Bitcoin Ticker offers simple Firebase authentication using email for user registration and login.

### Favorites Page and Cloud Firestore

Users can add their favorite coins to the Cloud Firestore database by clicking the "Add to Favorites" button on the detail screen. These favorite coins can then be accessed on the "Favorites" page.

## Technologies Used 

- [Material Design for Android](https://developer.android.com/develop/ui/views/theming/look-and-feel)
- [Dependency injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

## Installation

Clone this repository to your local machine using Git:

```bash
git clone https://github.com/yourusername/bitcoin-ticker.git

