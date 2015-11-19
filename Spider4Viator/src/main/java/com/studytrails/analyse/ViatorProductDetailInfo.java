/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: dongxun
 * Date: 2015年3月19日
 * Description:ViatorProductDetail.java 
 */
package com.studytrails.analyse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * viator的产品详情类
 * 
 * @author dongxun
 */
public class ViatorProductDetailInfo {
    
    private String errorReference;
    
    private ProductDetailData data;
    
    private String dateStamp;
    
    private String errorType;
    
    private String errorMessage;
    
    private String errorName;
    
    private boolean success;
    
    private Integer totalCount;
    
    private Integer vmid;
    
    private String errorMessageText;
    
    public String getErrorReference() {
        return errorReference;
    }

    public void setErrorReference(String errorReference) {
        this.errorReference = errorReference;
    }

    public ProductDetailData getData() {
        return data;
    }

    public void setData(ProductDetailData data) {
        this.data = data;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getVmid() {
        return vmid;
    }

    public void setVmid(Integer vmid) {
        this.vmid = vmid;
    }

    public String getErrorMessageText() {
        return errorMessageText;
    }

    public void setErrorMessageText(String errorMessageText) {
        this.errorMessageText = errorMessageText;
    }

    public static class ProductDetailData {
        private String supplierName;
        
        //币种编号
        private String currencyCode;
        
        private List<Integer> catIds;
        
        private List<Integer> subCatIds;
        
        private String webURL;
        
        private String specialReservationDetails;
        
        private Integer panoramaCount;
        
        private boolean merchantCancellable;
        
        private String bookingEngineId;
        
        private String onRequestPeriod;
        
        private String primaryGroupId;
        
        private String voucherRequirements;
        
        private boolean tourGradesAvailable;
        
        private boolean hotelPickup;
        
        private List<UserPhotos> userPhotos;
        
        private List<Reviews> reviews;
        
        private String videos;
        
        private List<TourGrades> tourGrades;
        
        private List<AgeBands> ageBands;
        
        private List<BookingQuestions> bookingQuestions;
        
        private String highlights;
        
        private List<String> salesPoints;
        
        private Map<String, Integer> ratingCounts;
        
        private String termsAndConditions;
        
        private String itinerary;
        
        private String departureTime;
        
        private String departureTimeComments;
        
        private String departurePoint;
        
        private List<String> inclusions;
        
        private String voucherOption;
        
        private List<String> exclusions; 
        
        private Integer destinationId;
        
        private List<ProductPhotos> productPhotos;
        
        private String city;
        
        private List<String> additionalInfo;
        
        private String operates;
        
        private String returnDetails;
        
        private String mapURL;
        
        private String specialOffer;
        
        private String description;
        
        private String location;
        
        private String country;
        
        private String region;
        
        private String shortDescription;
        
        private BigDecimal price;
        
        private String title;
        
        private boolean specialReservation;
        
        private double rating;
        
        private String thumbnailURL;
        
        private Integer photoCount;
        
        private Integer reviewCount;
        
        private String supplierCode;
        
        private boolean onSale;
        
        private double rrp;
        
        private String rrpformatted;
        
        private double savingAmount;
        
        private String savingAmountFormated;
        
        private Integer videoCount;
        
        private String shortTitle;
        
        private Integer translationLevel;
        
        private Integer primaryDestinationId;
        
        private String primaryDestinationName;
        
        private String priceFormatted;
        
        private BigDecimal merchantNetPriceFrom;
        
        private String merchantNetPriceFromFormatted;
        
        private boolean specialOfferAvailable;
        
        private String thumbnailHiResURL;
        
        private String duration;
        
        private String code;
        
        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public List<Integer> getCatIds() {
            return catIds;
        }

        public void setCatIds(List<Integer> catIds) {
            this.catIds = catIds;
        }

        public List<Integer> getSubCatIds() {
            return subCatIds;
        }

        public void setSubCatIds(List<Integer> subCatIds) {
            this.subCatIds = subCatIds;
        }

        public String getWebURL() {
            return webURL;
        }

        public void setWebURL(String webURL) {
            this.webURL = webURL;
        }

        public String getSpecialReservationDetails() {
            return specialReservationDetails;
        }

        public void setSpecialReservationDetails(String specialReservationDetails) {
            this.specialReservationDetails = specialReservationDetails;
        }

        public Integer getPanoramaCount() {
            return panoramaCount;
        }

        public void setPanoramaCount(Integer panoramaCount) {
            this.panoramaCount = panoramaCount;
        }

        public boolean isMerchantCancellable() {
            return merchantCancellable;
        }

        public void setMerchantCancellable(boolean merchantCancellable) {
            this.merchantCancellable = merchantCancellable;
        }

        public String getBookingEngineId() {
            return bookingEngineId;
        }

        public void setBookingEngineId(String bookingEngineId) {
            this.bookingEngineId = bookingEngineId;
        }

        public String getOnRequestPeriod() {
            return onRequestPeriod;
        }

        public void setOnRequestPeriod(String onRequestPeriod) {
            this.onRequestPeriod = onRequestPeriod;
        }

        public String getPrimaryGroupId() {
            return primaryGroupId;
        }

        public void setPrimaryGroupId(String primaryGroupId) {
            this.primaryGroupId = primaryGroupId;
        }

        public String getVoucherRequirements() {
            return voucherRequirements;
        }

        public void setVoucherRequirements(String voucherRequirements) {
            this.voucherRequirements = voucherRequirements;
        }

        public boolean isTourGradesAvailable() {
            return tourGradesAvailable;
        }

        public void setTourGradesAvailable(boolean tourGradesAvailable) {
            this.tourGradesAvailable = tourGradesAvailable;
        }

        public boolean isHotelPickup() {
            return hotelPickup;
        }

        public void setHotelPickup(boolean hotelPickup) {
            this.hotelPickup = hotelPickup;
        }

        public List<UserPhotos> getUserPhotos() {
            return userPhotos;
        }

        public void setUserPhotos(List<UserPhotos> userPhotos) {
            this.userPhotos = userPhotos;
        }

        public List<Reviews> getReviews() {
            return reviews;
        }

        public void setReviews(List<Reviews> reviews) {
            this.reviews = reviews;
        }

        public String getVideos() {
            return videos;
        }

        public void setVideos(String videos) {
            this.videos = videos;
        }

        public List<TourGrades> getTourGrades() {
            return tourGrades;
        }

        public void setTourGrades(List<TourGrades> tourGrades) {
            this.tourGrades = tourGrades;
        }

        public List<AgeBands> getAgeBands() {
            return ageBands;
        }

        public void setAgeBands(List<AgeBands> ageBands) {
            this.ageBands = ageBands;
        }

        public List<BookingQuestions> getBookingQuestions() {
            return bookingQuestions;
        }
        
        public void setBookingQuestions(List<BookingQuestions> bookingQuestions) {
            this.bookingQuestions = bookingQuestions;
        }

        public String getHighlights() {
            return highlights;
        }

        public void setHighlights(String highlights) {
            this.highlights = highlights;
        }

        public List<String> getSalesPoints() {
            return salesPoints;
        }

        public void setSalesPoints(List<String> salesPoints) {
            this.salesPoints = salesPoints;
        }

        public Map<String, Integer> getRatingCounts() {
            return ratingCounts;
        }

        public void setRatingCounts(Map<String, Integer> ratingCounts) {
            this.ratingCounts = ratingCounts;
        }

        public String getTermsAndConditions() {
            return termsAndConditions;
        }

        public void setTermsAndConditions(String termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
        }

        public String getItinerary() {
            return itinerary;
        }

        public void setItinerary(String itinerary) {
            this.itinerary = itinerary;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
        }

        public String getDepartureTimeComments() {
            return departureTimeComments;
        }

        public void setDepartureTimeComments(String departureTimeComments) {
            this.departureTimeComments = departureTimeComments;
        }

        public String getDeparturePoint() {
            return departurePoint;
        }

        public void setDeparturePoint(String departurePoint) {
            this.departurePoint = departurePoint;
        }

        public List<String> getInclusions() {
            return inclusions;
        }

        public void setInclusions(List<String> inclusions) {
            this.inclusions = inclusions;
        }

        public String getVoucherOption() {
            return voucherOption;
        }

        public void setVoucherOption(String voucherOption) {
            this.voucherOption = voucherOption;
        }

        public List<String> getExclusions() {
            return exclusions;
        }

        public void setExclusions(List<String> exclusions) {
            this.exclusions = exclusions;
        }

        public Integer getDestinationId() {
            return destinationId;
        }

        public void setDestinationId(Integer destinationId) {
            this.destinationId = destinationId;
        }

        public List<ProductPhotos> getProductPhotos() {
            return productPhotos;
        }

        public void setProductPhotos(List<ProductPhotos> productPhotos) {
            this.productPhotos = productPhotos;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<String> getAdditionalInfo() {
            return additionalInfo;
        }

        public void setAdditionalInfo(List<String> additionalInfo) {
            this.additionalInfo = additionalInfo;
        }

        public String getOperates() {
            return operates;
        }

        public void setOperates(String operates) {
            this.operates = operates;
        }

        public String getReturnDetails() {
            return returnDetails;
        }

        public void setReturnDetails(String returnDetails) {
            this.returnDetails = returnDetails;
        }

        public String getMapURL() {
            return mapURL;
        }

        public void setMapURL(String mapURL) {
            this.mapURL = mapURL;
        }

        public String getSpecialOffer() {
            return specialOffer;
        }

        public void setSpecialOffer(String specialOffer) {
            this.specialOffer = specialOffer;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isSpecialReservation() {
            return specialReservation;
        }

        public void setSpecialReservation(boolean specialReservation) {
            this.specialReservation = specialReservation;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }

        public void setThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
        }

        public Integer getPhotoCount() {
            return photoCount;
        }

        public void setPhotoCount(Integer photoCount) {
            this.photoCount = photoCount;
        }

        public Integer getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(Integer reviewCount) {
            this.reviewCount = reviewCount;
        }

        public String getSupplierCode() {
            return supplierCode;
        }

        public void setSupplierCode(String supplierCode) {
            this.supplierCode = supplierCode;
        }

        public boolean isOnSale() {
            return onSale;
        }

        public void setOnSale(boolean onSale) {
            this.onSale = onSale;
        }

        public double getRrp() {
            return rrp;
        }

        public void setRrp(double rrp) {
            this.rrp = rrp;
        }

        public String getRrpformatted() {
            return rrpformatted;
        }

        public void setRrpformatted(String rrpformatted) {
            this.rrpformatted = rrpformatted;
        }

        public double getSavingAmount() {
            return savingAmount;
        }

        public void setSavingAmount(double savingAmount) {
            this.savingAmount = savingAmount;
        }

        public String getSavingAmountFormated() {
            return savingAmountFormated;
        }

        public void setSavingAmountFormated(String savingAmountFormated) {
            this.savingAmountFormated = savingAmountFormated;
        }

        public Integer getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(Integer videoCount) {
            this.videoCount = videoCount;
        }

        public String getShortTitle() {
            return shortTitle;
        }

        public void setShortTitle(String shortTitle) {
            this.shortTitle = shortTitle;
        }

        public Integer getTranslationLevel() {
            return translationLevel;
        }

        public void setTranslationLevel(Integer translationLevel) {
            this.translationLevel = translationLevel;
        }

        public Integer getPrimaryDestinationId() {
            return primaryDestinationId;
        }

        public void setPrimaryDestinationId(Integer primaryDestinationId) {
            this.primaryDestinationId = primaryDestinationId;
        }

        public String getPrimaryDestinationName() {
            return primaryDestinationName;
        }

        public void setPrimaryDestinationName(String primaryDestinationName) {
            this.primaryDestinationName = primaryDestinationName;
        }

        public String getPriceFormatted() {
            return priceFormatted;
        }

        public void setPriceFormatted(String priceFormatted) {
            this.priceFormatted = priceFormatted;
        }

        public BigDecimal getMerchantNetPriceFrom() {
            return merchantNetPriceFrom;
        }

        public void setMerchantNetPriceFrom(BigDecimal merchantNetPriceFrom) {
            this.merchantNetPriceFrom = merchantNetPriceFrom;
        }

        public String getMerchantNetPriceFromFormatted() {
            return merchantNetPriceFromFormatted;
        }

        public void setMerchantNetPriceFromFormatted(String merchantNetPriceFromFormatted) {
            this.merchantNetPriceFromFormatted = merchantNetPriceFromFormatted;
        }

        public boolean isSpecialOfferAvailable() {
            return specialOfferAvailable;
        }

        public void setSpecialOfferAvailable(boolean specialOfferAvailable) {
            this.specialOfferAvailable = specialOfferAvailable;
        }

        public String getThumbnailHiResURL() {
            return thumbnailHiResURL;
        }

        public void setThumbnailHiResURL(String thumbnailHiResURL) {
            this.thumbnailHiResURL = thumbnailHiResURL;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
        
        public static class UserPhotos {
            private Integer sortOrder;
            
            private String ownerName;
            
            private String ownerCountry;
            
            private String productTitle;
            
            private String ownerAvatarURL;
            
            private String title;
            
            private String thumbnailURL;
            
            private String productCode;
            
            private String caption;
            
            private Integer ownerId;
            
            private boolean editorsPick;
            
            private Integer photoId;
            
            private String photoURL;
            
            private String photoHiResURL;
            
            private String photoMediumResURL;
            
            private String timeUploaded;

            public Integer getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(Integer sortOrder) {
                this.sortOrder = sortOrder;
            }

            public String getOwnerName() {
                return ownerName;
            }

            public void setOwnerName(String ownerName) {
                this.ownerName = ownerName;
            }

            public String getOwnerCountry() {
                return ownerCountry;
            }

            public void setOwnerCountry(String ownerCountry) {
                this.ownerCountry = ownerCountry;
            }

            public String getProductTitle() {
                return productTitle;
            }

            public void setProductTitle(String productTitle) {
                this.productTitle = productTitle;
            }

            public String getOwnerAvatarURL() {
                return ownerAvatarURL;
            }

            public void setOwnerAvatarURL(String ownerAvatarURL) {
                this.ownerAvatarURL = ownerAvatarURL;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumbnailURL() {
                return thumbnailURL;
            }

            public void setThumbnailURL(String thumbnailURL) {
                this.thumbnailURL = thumbnailURL;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getCaption() {
                return caption;
            }

            public void setCaption(String caption) {
                this.caption = caption;
            }

            public Integer getOwnerId() {
                return ownerId;
            }

            public void setOwnerId(Integer ownerId) {
                this.ownerId = ownerId;
            }

            public boolean isEditorsPick() {
                return editorsPick;
            }

            public void setEditorsPick(boolean editorsPick) {
                this.editorsPick = editorsPick;
            }

            public Integer getPhotoId() {
                return photoId;
            }

            public void setPhotoId(Integer photoId) {
                this.photoId = photoId;
            }

            public String getPhotoURL() {
                return photoURL;
            }

            public void setPhotoURL(String photoURL) {
                this.photoURL = photoURL;
            }

            public String getPhotoHiResURL() {
                return photoHiResURL;
            }

            public void setPhotoHiResURL(String photoHiResURL) {
                this.photoHiResURL = photoHiResURL;
            }

            public String getPhotoMediumResURL() {
                return photoMediumResURL;
            }

            public void setPhotoMediumResURL(String photoMediumResURL) {
                this.photoMediumResURL = photoMediumResURL;
            }

            public String getTimeUploaded() {
                return timeUploaded;
            }

            public void setTimeUploaded(String timeUploaded) {
                this.timeUploaded = timeUploaded;
            }
        }

        public static class Reviews {
            private Integer sortOrder;
            
            private String ownerName;
            
            private String ownerCountry;
            
            private String productTitle;
            
            private String ownerAvatarURL;
            
            private String review;
            
            private Integer rating;
            
            private String productCode;
            
            private String publishedDate;
            
            private Integer ownerId;
            
            private String viatorFeedback;
            
            private String submissionDate;
            
            private String viatorNotes;
            
            private Integer reviewId;

            public Integer getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(Integer sortOrder) {
                this.sortOrder = sortOrder;
            }

            public String getOwnerName() {
                return ownerName;
            }

            public void setOwnerName(String ownerName) {
                this.ownerName = ownerName;
            }

            public String getOwnerCountry() {
                return ownerCountry;
            }

            public void setOwnerCountry(String ownerCountry) {
                this.ownerCountry = ownerCountry;
            }

            public String getProductTitle() {
                return productTitle;
            }

            public void setProductTitle(String productTitle) {
                this.productTitle = productTitle;
            }

            public String getOwnerAvatarURL() {
                return ownerAvatarURL;
            }

            public void setOwnerAvatarURL(String ownerAvatarURL) {
                this.ownerAvatarURL = ownerAvatarURL;
            }

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }

            public Integer getRating() {
                return rating;
            }

            public void setRating(Integer rating) {
                this.rating = rating;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getPublishedDate() {
                return publishedDate;
            }

            public void setPublishedDate(String publishedDate) {
                this.publishedDate = publishedDate;
            }

            public Integer getOwnerId() {
                return ownerId;
            }

            public void setOwnerId(Integer ownerId) {
                this.ownerId = ownerId;
            }

            public String getViatorFeedback() {
                return viatorFeedback;
            }

            public void setViatorFeedback(String viatorFeedback) {
                this.viatorFeedback = viatorFeedback;
            }

            public String getSubmissionDate() {
                return submissionDate;
            }

            public void setSubmissionDate(String submissionDate) {
                this.submissionDate = submissionDate;
            }

            public String getViatorNotes() {
                return viatorNotes;
            }

            public void setViatorNotes(String viatorNotes) {
                this.viatorNotes = viatorNotes;
            }

            public Integer getReviewId() {
                return reviewId;
            }

            public void setReviewId(Integer reviewId) {
                this.reviewId = reviewId;
            }
        }
        
        public static class TourGrades {
            private Integer sortOrder;
            
            private String currencyCode;

            private Map<String, String> langServices;
            
            private String gradeCode;
            
            private String priceFromFormatted;
            
            private BigDecimal priceFrom;
            
            private BigDecimal merchantNetPriceFrom;
            
            private String merchantNetPriceFromFormatted;
            
            private String gradeTitle;
            
            private String gradeDepartureTime;
            
            private String gradeDescription;
            
            private String defaultLanguageCode;

            public Integer getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(Integer sortOrder) {
                this.sortOrder = sortOrder;
            }

            public String getCurrencyCode() {
                return currencyCode;
            }

            public void setCurrencyCode(String currencyCode) {
                this.currencyCode = currencyCode;
            }

            public Map<String, String> getLangServices() {
                return langServices;
            }

            public void setLangServices(Map<String, String> langServices) {
                this.langServices = langServices;
            }

            public String getGradeCode() {
                return gradeCode;
            }

            public void setGradeCode(String gradeCode) {
                this.gradeCode = gradeCode;
            }

            public String getPriceFromFormatted() {
                return priceFromFormatted;
            }

            public void setPriceFromFormatted(String priceFromFormatted) {
                this.priceFromFormatted = priceFromFormatted;
            }

            public BigDecimal getPriceFrom() {
                return priceFrom;
            }

            public void setPriceFrom(BigDecimal priceFrom) {
                this.priceFrom = priceFrom;
            }

            public BigDecimal getMerchantNetPriceFrom() {
                return merchantNetPriceFrom;
            }

            public void setMerchantNetPriceFrom(BigDecimal merchantNetPriceFrom) {
                this.merchantNetPriceFrom = merchantNetPriceFrom;
            }

            public String getMerchantNetPriceFromFormatted() {
                return merchantNetPriceFromFormatted;
            }

            public void setMerchantNetPriceFromFormatted(String merchantNetPriceFromFormatted) {
                this.merchantNetPriceFromFormatted = merchantNetPriceFromFormatted;
            }

            public String getGradeTitle() {
                return gradeTitle;
            }

            public void setGradeTitle(String gradeTitle) {
                this.gradeTitle = gradeTitle;
            }

            public String getGradeDepartureTime() {
                return gradeDepartureTime;
            }

            public void setGradeDepartureTime(String gradeDepartureTime) {
                this.gradeDepartureTime = gradeDepartureTime;
            }

            public String getGradeDescription() {
                return gradeDescription;
            }

            public void setGradeDescription(String gradeDescription) {
                this.gradeDescription = gradeDescription;
            }

            public String getDefaultLanguageCode() {
                return defaultLanguageCode;
            }

            public void setDefaultLanguageCode(String defaultLanguageCode) {
                this.defaultLanguageCode = defaultLanguageCode;
            }
        }
        
        public static class AgeBands {
            private Integer sortOrder;
            
            private Integer ageFrom;
            
            private Integer ageTo;
            
            private Integer bandId;
            
            private String pluralDescription;
            
            private boolean adult;
            
            private boolean treatAsAdult;
            
            private String description;
            
            private Integer count;

            public Integer getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(Integer sortOrder) {
                this.sortOrder = sortOrder;
            }

            public Integer getAgeFrom() {
                return ageFrom;
            }

            public void setAgeFrom(Integer ageFrom) {
                this.ageFrom = ageFrom;
            }

            public Integer getAgeTo() {
                return ageTo;
            }

            public void setAgeTo(Integer ageTo) {
                this.ageTo = ageTo;
            }

            public Integer getBandId() {
                return bandId;
            }

            public void setBandId(Integer bandId) {
                this.bandId = bandId;
            }

            public String getPluralDescription() {
                return pluralDescription;
            }

            public void setPluralDescription(String pluralDescription) {
                this.pluralDescription = pluralDescription;
            }

            public boolean isAdult() {
                return adult;
            }

            public void setAdult(boolean adult) {
                this.adult = adult;
            }

            public boolean isTreatAsAdult() {
                return treatAsAdult;
            }

            public void setTreatAsAdult(boolean treatAsAdult) {
                this.treatAsAdult = treatAsAdult;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Integer getCount() {
                return count;
            }

            public void setCount(Integer count) {
                this.count = count;
            }
        }
        
        public static class BookingQuestions {
            private Integer sortOrder;
            
            private String title;
            
            private Integer questionId;
            
            private String subTitle;
            
            private boolean required;
            
            private String message;

            public Integer getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(Integer sortOrder) {
                this.sortOrder = sortOrder;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Integer getQuestionId() {
                return questionId;
            }

            public void setQuestionId(Integer questionId) {
                this.questionId = questionId;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public boolean isRequired() {
                return required;
            }

            public void setRequired(boolean required) {
                this.required = required;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
        
        public static class ProductPhotos {
            private String photoURL;
            
            private String caption;
            
            private String supplier;

            public String getPhotoURL() {
                return photoURL;
            }

            public void setPhotoURL(String photoURL) {
                this.photoURL = photoURL;
            }

            public String getCaption() {
                return caption;
            }

            public void setCaption(String caption) {
                this.caption = caption;
            }

            public String getSupplier() {
                return supplier;
            }

            public void setSupplier(String supplier) {
                this.supplier = supplier;
            }
        }
    }
}
